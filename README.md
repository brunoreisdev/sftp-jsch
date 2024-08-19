# sftp-jsch
Projeto de exemplo utilizando a lib `JSCH`  para realizar a comunicação com o servidor via `SFTP`


## Geração de SSH key

Após configurado o endpoint será necessário criar um `ssh-key` para utilizar, segue abaixo o comando para criar o `ssh-key` 

`ssh-keygen -t rsa -m PEM -f id_rsa -C ""` 

Após executar o comando acima irá solicitar uma frase para utilizar como senha, caso deseje utilizar deverá colocar algum valor

![Untitled (1)](https://github.com/user-attachments/assets/42da20d9-201b-4011-b92c-fa26ba8463c9)

Ao finalizar o processo terá sido criado um `ssh-key` dentro da pasta que você está conforme o nome que vocês passou no parâmetro `-f` , no exemplo acima foi utilizado `-f id_rsa` , portanto foram criados dois arquivos com esse nome `id_rsa` (chave privada) e `id_rsa.pub` (chave pública), como podemos ver na imagem abaixo:

![Untitled](https://github.com/user-attachments/assets/fbce5eca-6e92-4861-bb94-7500a5b18a80)

Com as chaves na mão será necessário adicionar a chave pública no servidor de destino, no exemplo acima como foi feito localmente adicionamos ele no arquivo `.ssh/authorized_keys` dentro do servidor.

A chave privada será utilizada para se autenticar, no caso deste exemplo ela está sendo utilizada nesse comando `jsch.addIdentity(privateKey)` .


### Adicionar host nos arquivos confiáveis (Known hosts)

Neste caso iremos gerar uma chave publica para adicionar no arquivo chamado `known_hosts` , segue abaixo o comando para a criação

`ssh-keyscan -t rsa <DNS_do_Server> >> known_hosts` 

exemplo 

```shell
$ ssh-keyscan -t rsa localhost >> known_hosts 
//retorno
# localhost:22 SSH-2.0-OpenSSH_9.6p1 Ubuntu-3ubuntu13
```

após executar o comando acima se formos no arquivo `known_hosts` veremos que foi adicionado uma chave neste arquivo para o host semelhante ao exemplo abaixo:

```shell
localhost ssh-rsa AAAAB3NzaC1yc2EAAAADAQABAAABgQC32NSBvzFck5dY+xmMBHLXoaBlatA66OQgQ2pkOORedVzIwVteZX7QzrVXDUeFpLhOD4AX8EwwfAMDLMgI7VqgR7lYPLhv+9KAAUbfjvBKa1OXKymRClEDiAOsuDx60HUPvQYy4BOWRfBDU/zI/IRmf5
```

Com esse valor adicionado já poderemos executar a aplicação para realizar os testes.

🚨🚨 ***OBS Verificar se o servidor aceita `RSA`, `ECDSA` ou algum outro, pois isso é crucial para a criação e funcionamento do código***
