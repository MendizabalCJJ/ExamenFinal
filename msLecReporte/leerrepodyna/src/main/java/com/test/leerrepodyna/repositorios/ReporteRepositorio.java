package com.test.leerrepodyna.repositorios;

import com.test.leerrepodyna.modelos.Reporte;

import org.socialsignin.spring.data.dynamodb.repository.EnableScan;
import org.springframework.data.repository.CrudRepository;

@EnableScan
public interface ReporteRepositorio extends CrudRepository<Reporte, String>{}
