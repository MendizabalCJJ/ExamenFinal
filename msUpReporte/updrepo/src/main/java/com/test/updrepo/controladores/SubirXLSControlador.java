package com.test.updrepo.controladores;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import com.test.updrepo.excepciones.RecursoNoEncontradoException;
import com.test.updrepo.servicios.BucketServicios;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping(path = "/reportes")
public class SubirXLSControlador {
    @Autowired
    private BucketServicios srv;

    @PostMapping(value = "/xls")
    public Object subirReporte(@RequestParam(value = "file", required = false) MultipartFile file) throws IOException,
        RecursoNoEncontradoException {
                String oriFileName = file.getOriginalFilename();

                Map<String, Object> mapResp = new HashMap<>();
                mapResp.put("archivo", oriFileName); 

                if(oriFileName != null && oriFileName.endsWith(".xls")){
                    srv.guardaReporte(oriFileName, file.getInputStream());
                    mapResp.put("subido", true);
                } else {
                    mapResp.put("subido", false);
                    mapResp.put("msg", "Tipo de Archivo Invalido");
                }

        return mapResp;
    }
}
