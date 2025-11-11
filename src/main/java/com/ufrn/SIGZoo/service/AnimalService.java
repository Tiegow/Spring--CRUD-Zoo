package com.ufrn.SIGZoo.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ufrn.SIGZoo.model.dto.AnimalDTO;
import com.ufrn.SIGZoo.model.entity.Animal;
import com.ufrn.SIGZoo.model.entity.Especie;
import com.ufrn.SIGZoo.model.entity.Recinto;
import com.ufrn.SIGZoo.model.entity.Veterinario;
import com.ufrn.SIGZoo.repository.AnimalRepository;
import com.ufrn.SIGZoo.repository.EspecieRepository;
import com.ufrn.SIGZoo.repository.RecintoRepository;
import com.ufrn.SIGZoo.repository.VeterinarioRepository;

import jakarta.persistence.EntityNotFoundException;

@Service
public class AnimalService {

    @Autowired
    private AnimalRepository animalRepository;

    @Autowired
    private VeterinarioRepository veterinarioRepository;

    @Autowired
    private EspecieRepository especieRepository;

    @Autowired
    private RecintoRepository recintoRepository;

    @Transactional
    public AnimalDTO criar(AnimalDTO dto) {
        Animal animal = dto.toEntity(); 

        setRelationshipsFromDto(animal, dto);
        
        Animal animalSalvo = animalRepository.save(animal);
        return AnimalDTO.fromEntity(animalSalvo);
    }

    @Transactional
    public void deletar(Integer id) {
        Animal animal = animalRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Animal não encontrado."));
        
        animalRepository.delete(animal);
    }

    @Transactional
    public AnimalDTO atualizar(Integer id, AnimalDTO dto) {
        Animal animalExistente = animalRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Animal não encontrado."));

        animalExistente.setNome(dto.getNome());
        animalExistente.setSexo(dto.getSexo());
        animalExistente.setNascimento(dto.getNascimento());
        animalExistente.setOrigem(dto.getOrigem());

        setRelationshipsFromDto(animalExistente, dto);
        
        Animal animalAtualizado = animalRepository.save(animalExistente);
        return AnimalDTO.fromEntity(animalAtualizado);
    }

    @Transactional(readOnly = true)
    public Page<AnimalDTO> listarTodos(Pageable pageable) {
        Page<Animal> animalPage = animalRepository.findAll(pageable);
        return animalPage.map(AnimalDTO::fromEntity);
    }

    public List<AnimalDTO> listarTodosList() {
        List<Animal> animalList = animalRepository.findAll();
        return animalList.stream().map(AnimalDTO::fromEntity).collect(Collectors.toList());
    }


    @Transactional(readOnly = true)
    public List<Animal> listarPorVeterinario(Integer idVet) {
        return animalRepository.findAllByVeterinarioId(idVet);
    }

    @Transactional(readOnly = true)
    public List<AnimalDTO> listarPorSexo(String sexo) {
        List<Animal> animais = animalRepository.findAllBySexo(sexo);
        return animais.stream().map(AnimalDTO::fromEntity).collect(Collectors.toList());
    }

    @Transactional(readOnly = true)
    public AnimalDTO buscarPorId(Integer id) {
        Animal animal = animalRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Animal não encontrado."));
        return AnimalDTO.fromEntity(animal);
    }

    @Transactional(readOnly = true)
    public long obterQtdAnimais() {
        return animalRepository.count();
    } 

    private void setRelationshipsFromDto(Animal animal, AnimalDTO dto) {
        if (dto.getEspecieId() != null) {
            Especie especie = especieRepository.findById(dto.getEspecieId())
                .orElseThrow(() -> new EntityNotFoundException("Espécie com ID " + dto.getEspecieId() + " não encontrada."));
            animal.setEspecie(especie);
        } else {
            animal.setEspecie(null);
        }

        if (dto.getRecintoId() != null) {
            Recinto recinto = recintoRepository.findById(dto.getRecintoId())
                .orElseThrow(() -> new EntityNotFoundException("Recinto com ID " + dto.getRecintoId() + " não encontrado."));
            if (recinto.getStatus().equals("FECHADO")) {
                throw new IllegalStateException("Não é possível atribuir um animal a um recinto fechado.");
            }
            animal.setRecinto(recinto);
        } else {
            animal.setRecinto(null);
        }

        if (dto.getVeterinarioId() != null) {
            Veterinario vet = veterinarioRepository.findById(dto.getVeterinarioId())
                .orElseThrow(() -> new EntityNotFoundException("Veterinário com ID " + dto.getVeterinarioId() + " não encontrado!"));
            animal.setVeterinario(vet);
        } else {
            animal.setVeterinario(null);
        }
    }
}
