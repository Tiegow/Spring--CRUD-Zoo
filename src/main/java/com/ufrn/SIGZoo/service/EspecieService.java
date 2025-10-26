package com.ufrn.SIGZoo.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.ufrn.SIGZoo.model.dto.EspecieDTO;
import com.ufrn.SIGZoo.model.entity.Especie;
import com.ufrn.SIGZoo.repository.EspecieRepository;
import org.springframework.transaction.annotation.Transactional;


import jakarta.persistence.EntityNotFoundException;

@Service
public class EspecieService {
    

    @Autowired
    private EspecieRepository especieRepository;
    
    //CUD - Create Update Delete
    @Transactional
    public EspecieDTO criar(EspecieDTO especieDTO){
        Especie especie = new Especie();

        especie.setNome(especieDTO.getNome());
        especie.setExpectativaVida(especieDTO.getExpectativaVida());
        especie.setAreaAdequada(especieDTO.getAreaAdequada());
        especie.setTamanhoMinimoGrupo(especieDTO.getTamanhoMinimoGrupo());
        especie.setTamanhoMaximoGrupo(especieDTO.getTamanhoMaximoGrupo());

        especieRepository.save(especie);
        return toDTO(especie);
    }

    @Transactional
    public void deletar(Integer id){

        Especie especie = especieRepository.findById(id)
        .orElseThrow(() -> new EntityNotFoundException("Espécie não encontrada."));

        especieRepository.delete(especie);
    }

    @Transactional
    public EspecieDTO atualizar(Integer id, EspecieDTO dto){
        Especie especieExistente = especieRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("Espécie não encotrada."));
    
        especieExistente.setNome(dto.getNome());
        especieExistente.setExpectativaVida(dto.getExpectativaVida());
        especieExistente.setAreaAdequada(dto.getAreaAdequada());
        especieExistente.setTamanhoMinimoGrupo(dto.getTamanhoMinimoGrupo());
        especieExistente.setTamanhoMaximoGrupo(dto.getTamanhoMaximoGrupo());

        especieRepository.save(especieExistente);
        return toDTO(especieExistente);
    }


    // Read
    @Transactional(readOnly = true)
    public Page<EspecieDTO> listarTodos(Pageable pageable){
        Page<Especie> pageEspecie = especieRepository.findAllByOrderByNome(pageable);
        return pageEspecie.map(this::toDTO);
    }

    @Transactional(readOnly = true)
    public EspecieDTO buscarPorId(Integer id){
        Especie especie = especieRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("Animal não encontrado."));;
        return toDTO(especie);
    }

    @Transactional(readOnly = true)
    public EspecieDTO buscarPorNome(String nome){
        Especie especie = especieRepository.findByNome(nome).orElseThrow(() -> new EntityNotFoundException("Animal não encontrado."));;
        return toDTO(especie);
    }

    @Transactional(readOnly = true)
    public List<EspecieDTO> buscarPorExpectativaVidaMenorQue(Integer idade) {
        List<Especie> especies = especieRepository.findByExpectativaVidaLessThan(idade);
        return listDTO(especies);
    }

    @Transactional(readOnly = true)
    public List<EspecieDTO> buscarPorExpectativaVidaMaiorQue(Integer idade) {
        List<Especie> especies = especieRepository.findByExpectativaVidaGreaterThan(idade);
        return listDTO(especies);
    }


    private EspecieDTO toDTO(Especie especie){

        if (especie == null){
            return null;
        }

        EspecieDTO dto = new EspecieDTO();

        dto.setNome(especie.getNome());
        dto.setExpectativaVida(especie.getExpectativaVida());
        dto.setAreaAdequada(especie.getAreaAdequada());
        dto.setTamanhoMinimoGrupo(especie.getTamanhoMinimoGrupo());
        dto.setTamanhoMaximoGrupo(especie.getTamanhoMaximoGrupo());

        return dto;
    }

    private List<EspecieDTO> listDTO(List<Especie> especies) {
        return especies.stream().map(this::toDTO).collect(Collectors.toList());
    }

}
