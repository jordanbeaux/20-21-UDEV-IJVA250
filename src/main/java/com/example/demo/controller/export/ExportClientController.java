package com.example.demo.controller.export;

import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.demo.service.export.ExportClientCSV;
import com.example.demo.service.export.ExportClientXlsx;



@Controller
@RequestMapping("export")
public class ExportClientController {

	@Autowired
	private ExportClientCSV exportClientCSV;
	
	@Autowired
	private ExportClientXlsx exportClientXlsx;
	
	@GetMapping("/clients/csv")
	public void clientsCSV(HttpServletRequest request, HttpServletResponse response) throws IOException {
        // positionne de metadata sur la réponse afin d'informer le navigateur que la réponse correspond à un fichier téléchargeable.
        response.setContentType("text/csv");
        response.setHeader("Content-Disposition", "attachment; filename=\"export-clients.csv\"");

        // Le writter est un objet provenant de la response dans lequel on va pouvoir écrire pour générer le contenu de l'export CSV.
        PrintWriter writer = response.getWriter();

        exportClientCSV.exportAll(writer);
        
	}
	
	@GetMapping("/clients/xlsx")
	public void clientsXlsx(HttpServletRequest request, HttpServletResponse response) throws IOException {
        // positionne de metadata sur la réponse afin d'informer le navigateur que la réponse correspond à un fichier téléchargeable.
        response.setContentType("application/vnd.ms-excel");
        response.setHeader("Content-Disposition", "attachment; filename=\"export-clients.xlsx\"");

        // Le writter est un objet provenant de la response dans lequel on va pouvoir écrire pour générer le contenu de l'export CSV.
        OutputStream outputStream = response.getOutputStream();

        exportClientXlsx.exportAll(outputStream);
        
	}
}
