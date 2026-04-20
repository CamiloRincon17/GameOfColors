/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package com.mycompany.juegodecolores;

/**
 *
 * @author URIEL MAURICIO
 */
public class MenuColorido extends javax.swing.JFrame {

    /**
     * Creates new form MenuColorido
     */
    // Mapa centralizado de colores (un solo lugar para todos los RGB)
    private final java.util.Map<String, java.awt.Color> mapaColores = new java.util.LinkedHashMap<>();
    
    public MenuColorido() {
        initComponents();
        
        // Definir colores en un solo lugar
        mapaColores.put("amarillo", new java.awt.Color(255, 223, 0));
        mapaColores.put("verde",    new java.awt.Color(50, 205, 50));
        mapaColores.put("rojo",     new java.awt.Color(220, 20, 60));
        mapaColores.put("rosa",     new java.awt.Color(255, 105, 180));
        mapaColores.put("naranja",  new java.awt.Color(255, 140, 0));
        mapaColores.put("azul",     new java.awt.Color(30, 144, 255));
        mapaColores.put("negro",     new java.awt.Color(0, 0, 0));
        
                mapaColores.put("blanco", new java.awt.Color(255, 255, 255));
        mapaColores.put("morado", new java.awt.Color(128, 0, 128));
        mapaColores.put("gris", new java.awt.Color(128, 128, 128));
        mapaColores.put("cyan", new java.awt.Color(0, 255, 255));
        mapaColores.put("marron", new java.awt.Color(139, 69, 19));
        mapaColores.put("lima", new java.awt.Color(50, 255, 50));
        mapaColores.put("magenta", new java.awt.Color(255, 0, 255));
        mapaColores.put("turquesa", new java.awt.Color(64, 224, 208));

        // --- INICIO DE ESTILIZACIÓN ---
        this.getContentPane().setBackground(new java.awt.Color(45, 45, 45));
        
        MostrarLista.setFont(new java.awt.Font("Segoe UI", java.awt.Font.BOLD, 16));
        MostrarLista.setForeground(java.awt.Color.WHITE);
        MostrarLista.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        MostrarLista.setText("Presiona un color para comenzar");
        
        this.setSize(500, 400);
        this.setLocationRelativeTo(null);
        
        // Estilizar botones
        javax.swing.JButton[] botones = {amarillo, verde, rojo, rosa, naranja, azul, negro, blanco, morado, gris, cyan, marron, lima, magenta, turquesa};
        String[] nombres = {"amarillo", "verde", "rojo", "rosa", "naranja", "azul", "negro", "blanco", "morado", "gris", "cyan", "marron", "lima", "magenta", "turquesa"};
        for (int i = 0; i < botones.length; i++) {
            botones[i].setFont(new java.awt.Font("Segoe UI", java.awt.Font.BOLD, 16));
            botones[i].setFocusPainted(false);
            botones[i].setBorder(javax.swing.BorderFactory.createMatteBorder(2, 2, 2, 2, new java.awt.Color(0, 0, 0, 80)));
            botones[i].setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
            botones[i].setBackground(mapaColores.get(nombres[i]));
        }
        
        // Letras oscuras para fondos claros, blancas para oscuros
        amarillo.setForeground(java.awt.Color.BLACK);
        naranja.setForeground(java.awt.Color.BLACK);
        rosa.setForeground(java.awt.Color.BLACK);
        verde.setForeground(java.awt.Color.BLACK);
        rojo.setForeground(java.awt.Color.WHITE);
        azul.setForeground(java.awt.Color.WHITE);
        negro.setForeground(java.awt.Color.WHITE);
        
        blanco.setForeground(java.awt.Color.BLACK);
        morado.setForeground(java.awt.Color.WHITE);
        gris.setForeground(java.awt.Color.BLACK);
        cyan.setForeground(java.awt.Color.BLACK);
        marron.setForeground(java.awt.Color.WHITE);
        lima.setForeground(java.awt.Color.BLACK);
        magenta.setForeground(java.awt.Color.WHITE);
        turquesa.setForeground(java.awt.Color.BLACK);

        // --- FIN DE ESTILIZACIÓN ---
    }
    
    JuegoDeColores objlista = new JuegoDeColores();
    
    // Función central que le pasa el color al backend y muestra el resultado
    private void procesarBoton(String color, javax.swing.JButton botonPresionado) {
        JuegoDeColores.Resultado resultado = objlista.procesarJuego(color);
        
        // Armar mensaje según el resultado
        String mensaje;
        switch (resultado) {
            case NUEVO_COLOR:
                mensaje = "RONDA " + objlista.getRonda() + " | " + objlista.getRonda() + " colores - REPITE desde el inicio!";
                break;
            case ACIERTO_PARCIAL:
                mensaje = "Correcto! (" + objlista.getAciertosEnRonda() + "/" + objlista.getRonda() + ") Sigue...";
                break;
            case SECUENCIA_COMPLETA:
                mensaje = "COMPLETA! +" + objlista.getUltimosPuntosGanados() + "pts | Puntaje: " + objlista.getPuntaje() + " | Agrega color (Ronda " + (objlista.getRonda() + 1) + ")";
                break;
            case PERDISTE:
                mensaje = "PERDISTE! Era: " + objlista.getColorEsperado()
                        + " | Puntaje: " + objlista.getPuntajeFinal()
                        + " | Rondas: " + objlista.getRondasCompletadas()
                        + " | Record: " + objlista.getMejorPuntaje()
                        + " | Presiona para reiniciar";
                break;
            case HOLA:
                mensaje = "HOLA";
                break;
            default:
                mensaje = "";
        }
        MostrarLista.setText(mensaje);
        
        // Feedback visual: color del mapa (ya no hay if/else duplicado)
        final java.awt.Color colorOriginal = mapaColores.get(color);
        botonPresionado.setBackground(resultado == JuegoDeColores.Resultado.PERDISTE ? java.awt.Color.RED : java.awt.Color.GREEN);
        
        // Timer para restaurar color original
        javax.swing.Timer timer = new javax.swing.Timer(400, e -> botonPresionado.setBackground(colorOriginal));
        timer.setRepeats(false);
        timer.start();
    }
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        amarillo = new javax.swing.JButton();
        verde = new javax.swing.JButton();
        rojo = new javax.swing.JButton();
        MostrarLista = new javax.swing.JLabel();
        rosa = new javax.swing.JButton();
        naranja = new javax.swing.JButton();
        azul = new javax.swing.JButton();
        negro = new javax.swing.JButton();
        blanco = new javax.swing.JButton();
        morado = new javax.swing.JButton();
        gris = new javax.swing.JButton();
        cyan = new javax.swing.JButton();
        marron = new javax.swing.JButton();
        lima = new javax.swing.JButton();
        magenta = new javax.swing.JButton();
        turquesa = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        amarillo.setText("Amarillo");
        amarillo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                amarilloActionPerformed(evt);
            }
        });

        verde.setText("Verde");
        verde.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                verdeActionPerformed(evt);
            }
        });

        rojo.setText("Rojo");
        rojo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                rojoActionPerformed(evt);
            }
        });

        MostrarLista.setText("ESCOJE PARA COMENZAR");

        rosa.setText("Rosa");
        rosa.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                rosaActionPerformed(evt);
            }
        });

        naranja.setText("Naranja");
        naranja.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                naranjaActionPerformed(evt);
            }
        });

        azul.setText("Azul");
        azul.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                azulActionPerformed(evt);
            }
        });

        negro.setText("negro");
        negro.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                negroActionPerformed(evt);
            }
        });

        blanco.setText("Blanco");
        blanco.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                blancoActionPerformed(evt);
            }
        });

        morado.setText("Morado");
        morado.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                moradoActionPerformed(evt);
            }
        });

        gris.setText("Gris");
        gris.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                grisActionPerformed(evt);
            }
        });

        cyan.setText("Cyan");
        cyan.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cyanActionPerformed(evt);
            }
        });

        marron.setText("Marron");
        marron.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                marronActionPerformed(evt);
            }
        });

        lima.setText("Lima");
        lima.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                limaActionPerformed(evt);
            }
        });

        magenta.setText("Magenta");
        magenta.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                magentaActionPerformed(evt);
            }
        });

        turquesa.setText("Turquesa");
        turquesa.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                turquesaActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(130, 130, 130)
                        .addComponent(MostrarLista, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addGap(20, 20, 20)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(rosa, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(amarillo, javax.swing.GroupLayout.DEFAULT_SIZE, 100, Short.MAX_VALUE))
                                .addGap(27, 27, 27)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(verde, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(naranja, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)))
                            .addGroup(layout.createSequentialGroup()
                                .addGap(34, 34, 34)
                                .addComponent(cyan)
                                .addGap(47, 47, 47)
                                .addComponent(marron)))
                        .addGap(28, 28, 28)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(azul, javax.swing.GroupLayout.PREFERRED_SIZE, 91, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(blanco))
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(rojo, javax.swing.GroupLayout.PREFERRED_SIZE, 91, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(lima))
                                .addGap(37, 37, 37)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(magenta)
                                    .addComponent(negro, javax.swing.GroupLayout.PREFERRED_SIZE, 81, javax.swing.GroupLayout.PREFERRED_SIZE))))
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addGap(52, 52, 52)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addComponent(morado)
                                    .addComponent(gris)))
                            .addGroup(layout.createSequentialGroup()
                                .addGap(42, 42, 42)
                                .addComponent(turquesa)))
                        .addGap(0, 84, Short.MAX_VALUE)))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(28, 28, 28)
                .addComponent(MostrarLista)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(verde, javax.swing.GroupLayout.PREFERRED_SIZE, 121, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(rojo, javax.swing.GroupLayout.PREFERRED_SIZE, 121, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(52, 52, 52)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(amarillo, javax.swing.GroupLayout.PREFERRED_SIZE, 121, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(negro, javax.swing.GroupLayout.PREFERRED_SIZE, 121, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(morado)))))
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(60, 60, 60)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(blanco)
                            .addComponent(gris)))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(50, 50, 50)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(azul, javax.swing.GroupLayout.PREFERRED_SIZE, 101, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(naranja, javax.swing.GroupLayout.PREFERRED_SIZE, 101, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(rosa, javax.swing.GroupLayout.PREFERRED_SIZE, 101, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(80, 80, 80)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(cyan)
                            .addComponent(marron)
                            .addComponent(lima)
                            .addComponent(magenta)
                            .addComponent(turquesa))))
                .addContainerGap(122, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void amarilloActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_amarilloActionPerformed
        procesarBoton("amarillo", amarillo);
    }//GEN-LAST:event_amarilloActionPerformed

    private void verdeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_verdeActionPerformed
        procesarBoton("verde", verde);
    }//GEN-LAST:event_verdeActionPerformed

    private void rojoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_rojoActionPerformed
        procesarBoton("rojo", rojo);
    }//GEN-LAST:event_rojoActionPerformed

    private void rosaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_rosaActionPerformed
        procesarBoton("rosa", rosa);
    }//GEN-LAST:event_rosaActionPerformed

    private void naranjaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_naranjaActionPerformed
        procesarBoton("naranja", naranja);
        // TODO add your handling code here:
    }//GEN-LAST:event_naranjaActionPerformed

    private void azulActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_azulActionPerformed
        procesarBoton("azul", azul);
        // TODO add your handling code here:
    }//GEN-LAST:event_azulActionPerformed

    private void negroActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_negroActionPerformed
        procesarBoton("negro", negro);

        // TODO add your handling code here:
    }//GEN-LAST:event_negroActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(MenuColorido.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(MenuColorido.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(MenuColorido.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(MenuColorido.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new MenuColorido().setVisible(true);
            }
        });
    }

    
    private void blancoActionPerformed(java.awt.event.ActionEvent evt) {
        procesarBoton("blanco", blanco);
    }
    
    private void moradoActionPerformed(java.awt.event.ActionEvent evt) {
        procesarBoton("morado", morado);
    }
    
    private void grisActionPerformed(java.awt.event.ActionEvent evt) {
        procesarBoton("gris", gris);
    }
    
    private void cyanActionPerformed(java.awt.event.ActionEvent evt) {
        procesarBoton("cyan", cyan);
    }
    
    private void marronActionPerformed(java.awt.event.ActionEvent evt) {
        procesarBoton("marron", marron);
    }
    
    private void limaActionPerformed(java.awt.event.ActionEvent evt) {
        procesarBoton("lima", lima);
    }
    
    private void magentaActionPerformed(java.awt.event.ActionEvent evt) {
        procesarBoton("magenta", magenta);
    }
    
    private void turquesaActionPerformed(java.awt.event.ActionEvent evt) {
        procesarBoton("turquesa", turquesa);
    }
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel MostrarLista;
    private javax.swing.JButton amarillo;
    private javax.swing.JButton azul;
    private javax.swing.JButton blanco;
    private javax.swing.JButton morado;
    private javax.swing.JButton gris;
    private javax.swing.JButton cyan;
    private javax.swing.JButton marron;
    private javax.swing.JButton lima;
    private javax.swing.JButton magenta;
    private javax.swing.JButton turquesa;
    private javax.swing.JButton naranja;
    private javax.swing.JButton negro;
    private javax.swing.JButton rojo;
    private javax.swing.JButton rosa;
    private javax.swing.JButton verde;
    // End of variables declaration//GEN-END:variables
}
