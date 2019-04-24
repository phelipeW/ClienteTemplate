public abstract class Template {
	
	protected String getSqlCriacao();
	protected boolean incluir(Object obj);
	protected boolean excluir(int id);
	protected boolean alterar(Object obj);
	
	public void CriarTabela(String nomeTabela){
		try ( Connection conn = DriverManager.getConnection("jdbc:derby:memory:database;create=true")) {

            log.info("Criando tabela "+ nomeTabela + "...");
            conn.createStatement().executeUpdate(this.getSqlCriacao());
			
		} catch (Exception e) {
            e.printStackTrace();
		}
    }
		
	public List<E> listarTodos(String nomeTabela) {

        List<E> resultado = new ArrayList<>();

        try ( Connection conn = DriverManager.getConnection("jdbc:derby:memory:database")) {

            String sql = "SELECT * FROM " + nomeTabela;

            Statement statement = conn.createStatement();
            ResultSet result = statement.executeQuery(sql);

            while (result.next()) {

                /*resultado.add(
                        PaisDTO.builder()
                                .codigoTelefone(result.getInt("codigoTelefone"))
                                .id(result.getInt("id"))
                                .nome(result.getString("nome"))
                                .sigla(result.getString("sigla"))
                                .build()
                );
				PS: NAO SOUBE DEIXAR ESSA PARTE GENERICA
				*/  
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return resultado;
    }
	
	public Object listarPorId (int id) {
        return this.listarTodos().stream().filter(p -> p.getId() == id).findAny().orElseThrow(RuntimeException::new);
    }
	
	
}

