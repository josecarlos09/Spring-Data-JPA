# Definição do senario do projeto. O que foi desenvolvido?

   A aplicação backend foi desenvolvida para modelar o cenário de uma livraria, com foco na gestão dos dados de livros, autores, editoras e resumos. O principal objetivo deste projeto é aplicar e gerenciar os diferentes tipos de relacionamentos entre as tabelas de um banco de dados relacional, utilizando o Spring Data JPA. Com isso, a aplicação explora as funcionalidades de mapeamento objeto-relacional (ORM) para criar, ler, atualizar e deletar dados, facilitando o desenvolvimento e a manutenção de consultas complexas que envolvem múltiplas entidades e suas relações.


# Etapas do desenvolvimento:

⦁	*Criação do projeto com MAVE* 

⦁	*Inserir dependências*

⦁	*Definir organização das pastas necessarias para o projeto:*
                (controllers, modells, repositorys, Dtos, services, SpringDoc)
                
⦁	*Conectar base de dados de um SGBD MySQL com Sprig Boot*

⦁	*Mapeamento das entidade*

⦁	*Definir os graus de relacionamento entre as entidades*: 
            
           @OneToMany: 1 para M 

           @ManyToOne: M para 1 

           @OneToOne: 1 para 1 

          @ManyToMany: M para M 

⦁	*Criação da interface JpaRepository*

⦁	*Realizar CRUD*

⦁	*Spring Doc*

