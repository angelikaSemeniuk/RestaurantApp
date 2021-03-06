/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package Clients;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.InetAddress;
import java.net.Socket;
import server.Server;

/**
 *
 * @author Vita
 */
public class ClientThread extends Thread{
    private Socket socket;
    private BufferedReader in;
    private PrintWriter out;
    public ClientThread(InetAddress addr) {
        try {
            socket = new Socket(addr, Server.PORT);
        } catch (IOException e) {
            System.err.println("IOException |?|?|?|?|?|??|");
        }
        try {
            in = new BufferedReader(new InputStreamReader(
                    socket.getInputStream()));
            out = new PrintWriter(new BufferedWriter(new OutputStreamWriter(
					socket.getOutputStream())), true);
            start();
        } catch (IOException e) {
            try {
                socket.close();
            } catch (IOException e2) {
                System.err.println("РЎРѕРєРµС‚ РЅРµ Р·Р°РєСЂРёС‚Рѕ");
            }
        }
    }
    public String checkEmployee(int id) throws IOException{
        out.println("{\"method\":"+"\"check\""+",\"params\":["+id+"]}");
        return(in.readLine());
    }
    
    @Override
    public void run() {  
    }
    
    public void closeThread(){
        try {
            socket.close();
        } catch (IOException e) {
            System.err.println("Socket not closed");
        }
    }

    public String toTable(int tableNumber, int userId) throws IOException {
        out.println("{\"method\":"+"\"addTable\""+",\"params\":[\""+tableNumber+"\",\""+userId+"\"]}");
        return(in.readLine());
    }

    public String getTables(int userId) throws IOException {
        out.println("{\"method\":"+"\"getTables\""+",\"params\":["+userId+"]}");
        return in.readLine();
    }
}
