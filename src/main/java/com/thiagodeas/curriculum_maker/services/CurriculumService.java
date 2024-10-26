package com.thiagodeas.curriculum_maker.services;

import com.itextpdf.text.*;
import com.itextpdf.text.pdf.PdfWriter;
import com.thiagodeas.curriculum_maker.models.UserProfile;
import org.springframework.stereotype.Service;

import java.io.ByteArrayOutputStream;

@Service
public class CurriculumService {

    public byte[] generatePdf(UserProfile userProfile) {
        Document document = new Document();
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();

        try {
            PdfWriter.getInstance(document, outputStream);
            document.open();

            Font fontTitle = new Font(Font.FontFamily.HELVETICA, 20, Font.NORMAL);
            document.add(new Paragraph(userProfile.getFullName(), fontTitle) {{
                setAlignment(Element.ALIGN_CENTER);
            }});

            Font fontSubtitle = new Font(Font.FontFamily.HELVETICA, 14, Font.NORMAL);
            document.add(new Paragraph(userProfile.getJobTitle(), fontSubtitle) {{
                setAlignment(Element.ALIGN_CENTER);
            }});;

            Font fontInfos = new Font(Font.FontFamily.HELVETICA, 12, Font.NORMAL);
            document.add(new Paragraph(userProfile.getNationality() + ", " + userProfile.getMaritalStatus(),
                    fontInfos) {{
                setAlignment(Element.ALIGN_CENTER);
            }});
            document.add(new Paragraph("Endereço: " + userProfile.getCity() + ", " + userProfile.getState() + ", " + userProfile.getCountry()) {{
                setAlignment(Element.ALIGN_CENTER);
            }});
            document.add(new Paragraph("Celular/Whatsapp: " + userProfile.getPhone()) {{
                setAlignment(Element.ALIGN_CENTER);
            }});
            document.add(new Paragraph("E-mail: " + userProfile.getEmail()) {{
                setAlignment(Element.ALIGN_CENTER);
            }});
            document.add(new Paragraph("LinkedIn: " + userProfile.getLinkedin()) {{
                setAlignment(Element.ALIGN_CENTER);
            }});
            document.add(new Paragraph("GitHub: " + userProfile.getGithub()) {{
                setAlignment(Element.ALIGN_CENTER);
            }});

            document.add(new Paragraph("\n"));

            Font sessionFont = new Font(Font.FontFamily.HELVETICA, 12, Font.UNDERLINE);
            document.add(new Paragraph("SOBRE MIM", sessionFont) {{
                setIndentationLeft(40);
            }});
            document.add(new Paragraph("\n"));

            document.add(new Paragraph(userProfile.getAboutMe(), fontInfos) {{
                setIndentationLeft(40);
            }});

            document.add(new Paragraph("\n"));
            document.add(new Paragraph("CONHECIMENTOS", sessionFont) {{
                setIndentationLeft(40);
            }});
            document.add(new Paragraph("\n"));

            document.add(new Paragraph("• FrontEnd: " + userProfile.getFrontEndKnowledge()) {{
                setIndentationLeft(40);
            }});
            document.add(new Paragraph("• BackEnd: " + userProfile.getBackEndKnowledge()) {{
                setIndentationLeft(40);
            }});
            document.add(new Paragraph("• Database: " + userProfile.getDatabaseKnowledge()) {{
                setIndentationLeft(40);
            }});
            document.add(new Paragraph("• Cloud: " + userProfile.getCloudKnowledge()) {{
                setIndentationLeft(40);
            }});
            document.add(new Paragraph("• Outros: " + userProfile.getOthersKnowledge()) {{
                setIndentationLeft(40);
            }});

            document.add(new Paragraph("\n"));
            document.add(new Paragraph("EDUCAÇÃO", sessionFont) {{
                setIndentationLeft(40);
            }});
            document.add(new Paragraph("\n"));

            userProfile.getEducation().forEach(education -> {
                try {
                    document.add(new Paragraph("• " + education.getCourse() + " | " + education.getInstitution() + " | " + education.getYear(), fontInfos) {{
                        setIndentationLeft(40);
                    }});
                } catch (DocumentException e) {
                    e.printStackTrace();
                }
            });

            document.add(new Paragraph("\n"));
            document.add(new Paragraph("PROJETOS", sessionFont) {{
                setIndentationLeft(40);
            }});
            document.add(new Paragraph("\n"));

            userProfile.getProjects().forEach(project -> {
                try {
                    document.add(new Paragraph("•  " + project.getTitle()) {{
                        setIndentationLeft(40);
                    }});
                    document.add(new Paragraph(project.getDescription()) {{
                        setIndentationLeft(50);
                                 }});
                    document.add(new Paragraph("\n"));
                } catch (DocumentException e) {
                    e.printStackTrace();
                }
            });

            document.add(new Paragraph("\n"));
            document.add(new Paragraph("ATIVIDADES ADICIONAIS", sessionFont) {{
                setIndentationLeft(40);
            }});
            document.add(new Paragraph("\n"));

            userProfile.getAdditionalActivities().forEach(additionalActivity -> {
                try {
                    document.add(new Paragraph("• " + additionalActivity.getDescription()) {{
                        setIndentationLeft(40);
                    }});
                    document.add(new Paragraph("\n"));
                } catch (DocumentException e) {
                    e.printStackTrace();
                }
            });
            document.close();
        } catch (DocumentException e) {
            e.printStackTrace();
        }
        return outputStream.toByteArray();
    }
}
