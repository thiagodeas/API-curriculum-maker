package com.thiagodeas.curriculum_maker.services;

import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Font;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfWriter;
import com.thiagodeas.curriculum_maker.models.UserProfile;
import org.springframework.stereotype.Service;

import java.io.FileOutputStream;
import java.io.IOException;

@Service
public class CurriculumService {

    public String generatePdf(UserProfile userProfile) {
        Document document = new Document();
        String filePath = "curriculumMaker_" + userProfile.getFullName() + ".pdf";

        try {
            PdfWriter.getInstance(document, new FileOutputStream(filePath));
            document.open();

            Font fontTitle = new Font(Font.FontFamily.HELVETICA, 20, Font.NORMAL);
            document.add(new Paragraph(userProfile.getFullName(), fontTitle));

            Font fontSubtitle = new Font(Font.FontFamily.HELVETICA, 14, Font.NORMAL);
            document.add(new Paragraph(userProfile.getJobTitle(), fontSubtitle));

            Font fontInfos = new Font(Font.FontFamily.HELVETICA, 12, Font.NORMAL);
            document.add(new Paragraph(userProfile.getNationality() + ", " + userProfile.getMaritalStatus(),
                    fontInfos));
            document.add(new Paragraph("Endereço: " + userProfile.getCity() + ", " + userProfile.getState() + ", " + userProfile.getCountry()));
            document.add(new Paragraph("Celular/Whatsapp: " + userProfile.getPhone()));
            document.add(new Paragraph("E-mail: " + userProfile.getEmail()));
            document.add(new Paragraph("LinkedIn: " + userProfile.getLinkedin()));
            document.add(new Paragraph("GitHub: " + userProfile.getGithub()));

            document.add(new Paragraph("\n"));

            Font sessionFont = new Font(Font.FontFamily.HELVETICA, 12, Font.UNDERLINE);
            document.add(new Paragraph("SOBRE MIM", sessionFont));
            document.add(new Paragraph("\n"));

            document.add(new Paragraph(userProfile.getAboutMe(), fontInfos));

            document.add(new Paragraph("\n"));
            document.add(new Paragraph("CONHECIMENTOS", sessionFont));
            document.add(new Paragraph("\n"));

            document.add(new Paragraph("•FrontEnd: " + userProfile.getFrontEndSkills()));
            document.add(new Paragraph("•BackEnd: " + userProfile.getBackEndSkills()));
            document.add(new Paragraph("•Database: " + userProfile.getDatabaseSkills()));
            document.add(new Paragraph("•Cloud: " + userProfile.getCloudSkills()));
            document.add(new Paragraph("•Outros: " + userProfile.getOtherSkills()));

            document.add(new Paragraph("\n"));
            document.add(new Paragraph("EDUCAÇÃO", sessionFont));
            document.add(new Paragraph("\n"));

            userProfile.getEducation().forEach(education -> {
                try {
                    document.add(new Paragraph("•" + education.getCourseName() + " | " + education.getInstitution() + " | " + education.getYear(), fontInfos));
                } catch (DocumentException e) {
                    e.printStackTrace();
                }
            });

            document.add(new Paragraph("\n"));
            document.add(new Paragraph("PROJETOS", sessionFont));
            document.add(new Paragraph("\n"));

            userProfile.getProjects().forEach(project -> {
                try {
                    document.add(new Paragraph("•" + project.getTitle()));
                    document.add(new Paragraph("•" + project.getDescription()));
                    document.add(new Paragraph("\n"));
                } catch (DocumentException e) {
                    e.printStackTrace();
                }
            });

            document.add(new Paragraph("\n"));
            document.add(new Paragraph("ATIVIDADES ADICIONAIS", sessionFont));
            document.add(new Paragraph("\n"));

            userProfile.getAdditionalActivities().forEach(additionalActivity -> {
                try {
                    document.add(new Paragraph("•" + additionalActivity.getDescription()));
                    document.add(new Paragraph("\n"));
                } catch (DocumentException e) {
                    e.printStackTrace();
                }
            });
            document.close();
        } catch (DocumentException | IOException e) {
            e.printStackTrace();
        }
        return filePath;
    }
}
