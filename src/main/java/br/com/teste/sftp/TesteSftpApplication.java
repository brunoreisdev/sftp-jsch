package br.com.teste.sftp;

import com.jcraft.jsch.*;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.io.File;

@SpringBootApplication
public class TesteSftpApplication {

  public static void main(String[] args) {
    SpringApplication.run(TesteSftpApplication.class, args);
  }
}
