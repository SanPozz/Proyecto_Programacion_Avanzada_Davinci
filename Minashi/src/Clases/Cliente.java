package Clases;


public class Cliente extends Usuario{
	
	private static Connection conn = Conexion.getInstance().getConnection();
	
    public Cliente(String nombre, String apellido, int edad, String correo, int rol) {
        super(nombre, apellido, edad, correo, rol);

    }


        public void emitirOrden() {
            try {

                String tipoMineral = JOptionPane.showInputDialog("Ingrese el tipo de mineral:");
                String purezaStr = JOptionPane.showInputDialog("Ingrese la pureza mínima:");
                double purezaMin = Double.parseDouble(purezaStr);

                
                PreparedStatement stmt = con.prepareStatement("SELECT * FROM minerales WHERE tipo = ? AND pureza >= ?");
                stmt.setString(1, tipoMineral);
                stmt.setDouble(2, purezaMin);
                ResultSet rs = stmt.executeQuery();

                List<Mineral> opciones = new ArrayList<>();
                while (rs.next()) {
                    Mineral m = new Mineral(
                        rs.getInt("idMineral"),
                        rs.getString("tipo"),
                        rs.getDouble("pureza"),
                        rs.getString("mina")
                    );
                    opciones.add(m);
                }

                if (opciones.isEmpty()) {
                    JOptionPane.showMessageDialog(null, "No se encontraron minerales.");
                    return;
                }

                
                Mineral[] arrayMinerales = opciones.toArray(new Mineral[0]);

                Mineral seleccionado = (Mineral) JOptionPane.showInputDialog(null, "Seleccione un mineral:", "Opciones", JOptionPane.PLAIN_MESSAGE, null, arrayMinerales, arrayMinerales[0]);

                if (seleccionado != null) {
                    List<Mineral> lista = new ArrayList<>();
                    lista.add(seleccionado);
                    java.sql.Date fecha = new java.sql.Date(System.currentTimeMillis());
                    double total = calcularPrecio(seleccionado);  

                    PreparedStatement insertStmt = conn.prepareStatement("INSERT INTO  ordenes_de_compra ('idOrden', 'fecha', 'destinatario', 'total') VALUES (?,?,?,?)");
                    insertStmt.setString(1, this.nombre);
                    insertStmt.setString(2, seleccionado.getTipo()); 
                    insertStmt.setDate(3, fecha);
                    insertStmt.setDouble(4, total);
                    insertStmt.executeUpdate();

                    JOptionPane.showMessageDialog(null, "Pedido realizado.");
                }

            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        public void verMisOrdenes() {
            try {
                String sql = "SELECT * FROM ordenes_de_compra WHERE destinatario = ?";
                PreparedStatement stmt = conn.prepareStatement(sql);
                stmt.setString(1, this.nombre);
                ResultSet rs = stmt.executeQuery();

                StringBuilder resultado = new StringBuilder();

                while (rs.next()) {
                    resultado.append("Minerales: ").append(rs.getString("minerales")).append("\n").append("Fecha: ").append(rs.getDate("fecha")).append("\n").append("Total: ").append(rs.getDouble("total")).append("\n\n");
                }

                if (resultado.length() == 0) {
                    JOptionPane.showMessageDialog(null, "No tienes órdenes registradas.");
                } else {
                    JOptionPane.showMessageDialog(null, resultado.toString(), "Mis órdenes", JOptionPane.INFORMATION_MESSAGE);
                }

            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        private double calcularPrecio(Mineral m) {
            return 100 * m.getPureza();
        }
    }


