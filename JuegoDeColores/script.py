import os

form_path = r"c:\Users\URIEL MAURICIO\OneDrive\Desktop\GameOfColors\JuegoDeColores\src\main\java\com\mycompany\juegodecolores\MenuColorido.form"
java_path = r"c:\Users\URIEL MAURICIO\OneDrive\Desktop\GameOfColors\JuegoDeColores\src\main\java\com\mycompany\juegodecolores\MenuColorido.java"

mapping = {
    "jButton1": "blanco",
    "jButton2": "morado",
    "jButton3": "gris",
    "jButton4": "cyan",
    "jButton5": "marron",
    "jButton6": "lima",
    "jButton7": "magenta",
    "jButton8": "turquesa"
}

with open(form_path, "r", encoding="utf-8") as f:
    form_content = f.read()

for old, new in mapping.items():
    form_content = form_content.replace(f'id="{old}"', f'id="{new}"')
    form_content = form_content.replace(f'name="{old}"', f'name="{new}"')
    # Change displayed text
    form_content = form_content.replace(f'value="{old}"', f'value="{new.capitalize()}"')
    
    old_comp = f'<Component class="javax.swing.JButton" name="{new}">\n      <Properties>\n        <Property name="text" type="java.lang.String" value="{new.capitalize()}"/>\n      </Properties>\n    </Component>'
    new_comp = f'<Component class="javax.swing.JButton" name="{new}">\n      <Properties>\n        <Property name="text" type="java.lang.String" value="{new.capitalize()}"/>\n      </Properties>\n      <Events>\n        <EventHandler event="actionPerformed" listener="java.awt.event.ActionListener" parameters="java.awt.event.ActionEvent" handler="{new}ActionPerformed"/>\n      </Events>\n    </Component>'
    form_content = form_content.replace(old_comp, new_comp)

with open(form_path, "w", encoding="utf-8") as f:
    f.write(form_content)

##############################
# Now modify the java file
with open(java_path, "r", encoding="utf-8") as f:
    java_content = f.read()

for old, new in mapping.items():
    # Replace variable names in definitions
    java_content = java_content.replace(f'private javax.swing.JButton {old};', f'private javax.swing.JButton {new};')
    # Replace variable instantiation
    java_content = java_content.replace(f'{old} = new javax.swing.JButton();', f'{new} = new javax.swing.JButton();')
    
    # Replace setText
    java_content = java_content.replace(f'{old}.setText("{old}");', f'{new}.setText("{new.capitalize()}");')
    
    # Also replace anything left like layout lines
    java_content = java_content.replace(f' {old}', f' {new}')
    java_content = java_content.replace(f'({old}', f'({new}')

    # Add Action Listener in Java
    set_text_str = f'{new}.setText("{new.capitalize()}");'
    injection = f'''{set_text_str}
        {new}.addActionListener(new java.awt.event.ActionListener() {{
            public void actionPerformed(java.awt.event.ActionEvent evt) {{
                {new}ActionPerformed(evt);
            }}
        }});'''
    java_content = java_content.replace(set_text_str, injection)
            
    handler_injection = f'''
    private void {new}ActionPerformed(java.awt.event.ActionEvent evt) {{
        procesarBoton("{new}", {new});
    }}'''
    java_content = java_content.replace('// Variables declaration - do not modify', handler_injection + '\n    // Variables declaration - do not modify')

# Add map colors
color_map_injection = '''        mapaColores.put("blanco", new java.awt.Color(255, 255, 255));
        mapaColores.put("morado", new java.awt.Color(128, 0, 128));
        mapaColores.put("gris", new java.awt.Color(128, 128, 128));
        mapaColores.put("cyan", new java.awt.Color(0, 255, 255));
        mapaColores.put("marron", new java.awt.Color(139, 69, 19));
        mapaColores.put("lima", new java.awt.Color(50, 255, 50));
        mapaColores.put("magenta", new java.awt.Color(255, 0, 255));
        mapaColores.put("turquesa", new java.awt.Color(64, 224, 208));
'''

java_content = java_content.replace('// --- INICIO DE ESTILIZACIÓN ---', color_map_injection + '\n        // --- INICIO DE ESTILIZACIÓN ---')

botones_old = 'javax.swing.JButton[] botones = {amarillo, verde, rojo, rosa, naranja, azul,negro};'
botones_new = 'javax.swing.JButton[] botones = {amarillo, verde, rojo, rosa, naranja, azul, negro, blanco, morado, gris, cyan, marron, lima, magenta, turquesa};'
java_content = java_content.replace(botones_old, botones_new)
 
nombres_old = 'String[] nombres = {"amarillo", "verde", "rojo", "rosa", "naranja", "azul","negro"};'
nombres_new = 'String[] nombres = {"amarillo", "verde", "rojo", "rosa", "naranja", "azul", "negro", "blanco", "morado", "gris", "cyan", "marron", "lima", "magenta", "turquesa"};'
java_content = java_content.replace(nombres_old, nombres_new)

frgnd_injection = '''
        blanco.setForeground(java.awt.Color.BLACK);
        morado.setForeground(java.awt.Color.WHITE);
        gris.setForeground(java.awt.Color.BLACK);
        cyan.setForeground(java.awt.Color.BLACK);
        marron.setForeground(java.awt.Color.WHITE);
        lima.setForeground(java.awt.Color.BLACK);
        magenta.setForeground(java.awt.Color.WHITE);
        turquesa.setForeground(java.awt.Color.BLACK);
'''
java_content = java_content.replace('// --- FIN DE ESTILIZACIÓN ---', frgnd_injection + '\n        // --- FIN DE ESTILIZACIÓN ---')

with open(java_path, "w", encoding="utf-8") as f:
    f.write(java_content)
