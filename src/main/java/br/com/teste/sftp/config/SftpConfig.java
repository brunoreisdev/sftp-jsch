package br.com.teste.sftp.config;

import com.jcraft.jsch.JSch;
import com.jcraft.jsch.Session;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import com.jcraft.jsch.*;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Paths;


public class SftpConfig {

    private static String user = "remote_user";

    private static String host = "localhost";

    private static String privateKey = "remote_user";

    private static String passphrase = "";

    private static Integer port = 22;

    private static String password = "password1234";

    /* criar canal para acessar servidor sftp */
    public static ChannelSftp setupJsch() throws JSchException {
        Session jschSession = null;

        Channel sftp = null;

        JSch jsch = new JSch();

        // authenticate using private key
        jsch.addIdentity(privateKey);

        jsch.setKnownHosts("known_hosts");
        jschSession = jsch.getSession(user, host, port);

        // Utilizado para ignorar verificações de host
        //jschSession.setConfig("StrictkHostKeyChecking", "no");

        // authenticate using password
        jschSession.setPassword(password);

        // 10 seconds session timeout
        jschSession.connect();

        sftp = jschSession.openChannel("sftp");

        return  (ChannelSftp) sftp;

    }

    /* armazenar imagem servidor SFTP */
    public static void salvarImagemSftp(InputStream storedImage, String originalFilename) throws SftpException, JSchException, IOException {

        Session jschSession = null;

        Channel sftp = null;
        // transfer file from local to remote server
        ChannelSftp channelSftp = setupJsch();
        channelSftp.connect(50000);

        channelSftp.put(storedImage,channelSftp.getHome() + "/images/" + originalFilename );

        channelSftp.exit();

        System.out.println("Done");

    }

    /* buscar imagem servidor sftp e transferir para pasta local */
    public static void buscarImagemSftp(String originalFilename) throws SftpException, JSchException, IOException {

        Session jschSession = null;

        Channel sftp = null;
        // transfer file from local to remote server
        ChannelSftp channelSftp = setupJsch();
        channelSftp.connect(50000);

        channelSftp.get(channelSftp.getHome() + "/images/" + originalFilename, "C:\\Users\\Dell\\Desktop\\testeImagem" );

        channelSftp.exit();

        System.out.println("Done");

    }

    /* deletar imagem servidor sftp */
    public static void deletarImagemSftp(String nomeImagem) throws SftpException, JSchException, IOException {

        Session jschSession = null;

        Channel sftp = null;
        // transfer file from local to remote server
        ChannelSftp channelSftp = setupJsch();
        channelSftp.connect(50000);

        channelSftp.rm(channelSftp.getHome() + "/" + nomeImagem);

        channelSftp.exit();

        System.out.println("Done");

    }
}


