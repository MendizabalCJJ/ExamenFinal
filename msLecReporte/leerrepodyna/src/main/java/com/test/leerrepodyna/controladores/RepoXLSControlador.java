package com.test.leerrepodyna.controladores;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import com.test.leerrepodyna.modelos.Reporte;
import com.test.leerrepodyna.repositorios.ReporteRepositorio;
import com.test.leerrepodyna.servicios.BucketService;

import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class RepoXLSControlador {
    private static final Logger log = LoggerFactory.getLogger(RepoXLSControlador.class); 

    @Autowired
    private BucketService s3Srv;

    @Autowired
    private ReporteRepositorio repo;

    @PutMapping("/reportes/{file}")
    public Object getAndAddDyna(@PathVariable String file) {
        Map<String, Object> mapRes = new HashMap<>();
        
        log.info("Buscando Archivo " + file + "...");

        if(file.endsWith(".xls")){
            List<Reporte> data = buildReporteContent(file);

            for(Reporte rep:data){
                repo.save(rep);
            }

            mapRes.put("data", data);

        } else {
            mapRes.put("msg", "Formato de Archivo Invalido");
        }
        
        return mapRes;
    }

    private List<Reporte> buildReporteContent(String file){
        List<Reporte> reps = new ArrayList<>();

        try(InputStream is = s3Srv.getRepoXLS(file);
            HSSFWorkbook wb = new HSSFWorkbook(is);){
                HSSFSheet sheet = wb.getSheetAt(0);

            Iterator<Row> rowIter = sheet.iterator();

            while(rowIter.hasNext()){
                Row row = rowIter.next();

                if(row.getRowNum() != 0) {
                    Iterator<Cell> cellIter = row.cellIterator();
                    
                    int intCell = 1;

                    Reporte repo = new Reporte();
                    repo.setArchivo(file);

                    while(cellIter.hasNext()){
                        Cell cell = cellIter.next();

                        switch(intCell){
                            case 1:
                                repo.setIdProducto(String.valueOf(cell.getNumericCellValue()));
                            break;

                            case 3:
                                repo.setCantidad(String.valueOf(cell.getNumericCellValue()));
                            break;

                            case 4:
                                repo.setPrecioUnitario(String.valueOf(cell.getNumericCellValue()));
                            break;

                            default:
                                repo.setDescripcion(cell.getStringCellValue());
                            break;
                        }
                        ++intCell;
                    }

                    reps.add(repo);
                }
            }
        } catch(Exception e){
            log.error("Error al leer Archivo " + file + "! [" + e.getClass().getSimpleName() + "]");
        }

        return reps;
    }
}
