public class Main {
    public static void main(String[] args) {
        Cd cd = new Cd();
        DAO dao = new DAO();
        dao.conectar();
        int opcao;
        System.out.println("MENU:\n");
        System.out.println("OPÇÕES:\n");
        System.out.println(
                "1-listar\n" + "2-Inserir\n" + "3-Excluir\n" + "4-Atualizar\n" + "5-Sair\n");
        opcao = MyIO.readInt();

        switch (opcao) {
            case 1:
                // Mostrar usuários
                Cd[] cds = dao.getMotos();
                System.out.println("==== Mostrar usuários === ");

                for (int i = 0; i < cds.length; i++) {
                    System.out.println(cds[i].toString());
                }

            case 2:
                // Inserir um elemento na tabela
                if (dao.inserirMoto(Cd) == true) {
                    System.out.println("Inserção com sucesso -> " + Cd.toString());
                }

            case 3:
                // Excluir usuário
                dao.excluirCd(Cd.getId());
                break;

            case 4:
                // Atualizar usuário
                Cd.setModelo("scooter");
                dao.atualizarCd(Cd);

            case 5:
                dao.close();
                break;

            default:
                break;
        }

        dao.close();
    }
}
