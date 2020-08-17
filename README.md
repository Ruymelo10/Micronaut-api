# Micronaut-api QuakeParser
###### Appi feita com o framework Micronaut. O desenvolvimento do parser foi feito no repositório "github.com/Ruymelo10/Quake-parser". Após a conclusão do parser, foi criado a base de um api usando Micronaut e foi inserida nesta os arquivos que realizam o parser do arquivo games.log 
### Instruções para rodar
- Cerifique-se de que você tem uma versão Java 8 SDK ou acima instalada e também uma IDE adequada, como por exemplo o Intellij. Caso esteja no intellij, habilite annotation processing em compiler. 
- Com a pasta baixada, va no arquivo application.yml na pasta src/main/resources. Em seguida troque a variável "log-file" para o PATH do arquivo games.log na sua máquina
- No terminal, dentro da pasta complete insira o comando  ./gradlew build
- Ainda no terminal, insira o comando  ./gradlew run
- Será printado no terminal a lista de jogos. No browser, insira o endereço "http://localhost:8080/games/" e adiciona a id do jogo que você deseja saber as informações (lembrando que as Id's vão de 0 a 20). Caso deseje encerrrar, vá em outro terminal e digite "./gradlew --stop
" na diretorio do projeto.
