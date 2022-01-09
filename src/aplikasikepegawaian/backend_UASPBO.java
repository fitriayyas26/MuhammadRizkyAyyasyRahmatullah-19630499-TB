/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package aplikasikepegawaian;

import java.sql.Connection;
import java.sql.DriverManager; // Driver untuk Connector Mysql
import java.sql.Driver; // Driver untuk Connector Mysql
import java.sql.SQLException; //handle jenis jenis error
import java.sql.Statement;  // library yang digunakan membuat perintah menyimpan, ubah, hapus dan juga cari
import java.sql.ResultSet;
import java.sql.PreparedStatement;
import javax.swing.JOptionPane;

import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumn;
/**
 *
 * @author User
 */
public class backend_UASPBO {
    private String jdbcUrl="jdbc:mysql://localhost:3306/uaspbo";  //untuk menampung URL dan Database/koneksi ke databse
    private String username= "root";
    private String password ="";
    
    public backend_UASPBO(){} //method Statis
        //Overload constructor
        
        public backend_UASPBO(String url, String username, String password){
        
            try{
                Driver mysqldriver = new com.mysql.jdbc.Driver();
                DriverManager.registerDriver(mysqldriver); // Setting Driver Manager/ Mendaftarkan Driver
                
                Connection koneksi = DriverManager.getConnection(url, username, password) ;//menghubungkan koneksi dari driver manager
                System.out.println("Berhasil Dikoneksikan"); // Keterangan untuk driver berhasil dikoneksikan
            
            }catch(Exception e){
            
            }
        }
//function untuk menghubungkan database
        public Connection getkoneksi() throws SQLException{
        try{
                Driver mysqldriver = new com.mysql.jdbc.Driver();
                DriverManager.registerDriver(mysqldriver); // Setting Driver Manager/ Mendaftarkan Driver
                System.out.println("Berhasil Dikoneksikan");
        }catch(Exception e){
        
        }
        return DriverManager.getConnection(this.jdbcUrl, this.username, this.password); //menampung hasil Koneksi
        }
        
        public boolean duplicatekey(String tabel, String primarykey, String isi){
        boolean hasil = false;
        try{
            Statement sts = getkoneksi().createStatement();
            ResultSet rs = sts.executeQuery("SELECT * FROM "+tabel+" WHERE "+primarykey+" = '"+isi+"' ");
            hasil=rs.next(); // perubahan posisi isi FALSE atau True
        }catch (Exception e){
            System.err.println(e.toString());
            
        }
        return hasil;
        }
        
    public void SaveStaticAdminStatement(String user, String pass, String lvl){ //menggunakan library statement
        try{
            String SQL="INSERT INTO admin(username, password, level) VALUES ('"+user+"','"+pass+"','"+lvl+"')";
            Statement perintah = getkoneksi().createStatement();
            perintah.executeUpdate(SQL);
            int jumlah = perintah.executeUpdate(SQL);
        }catch (Exception e){
        }
        
    }
    
    public void SaveStaticAdminPrepared(String user, String pass, String lvl){ //menggunakan library Prepared statement
        try{
            String SQL = "INSERT INTO admin(username, password, level) VALUES (?,?,?)";
            
            PreparedStatement perintah = getkoneksi().prepareStatement(SQL);
            perintah.setString(1,user);
            perintah.setString(2,pass);
            perintah.setString(3,lvl);
            
            int jumlah = perintah.executeUpdate(SQL);
            System.out.println(jumlah);
            
        }catch (Exception e){
            System.out.println(e.toString());
        }
        
    }
    
    
    public String getFieldTabel(String[] Fieldnya){
    String hasil="";
    int deteksi = Fieldnya.length-1;
    
    try{
        for (int i=0; i < Fieldnya.length; i++){
            if(i==deteksi){
                hasil=hasil+Fieldnya[i];
            }else{
                hasil=hasil+Fieldnya[i]+",";
            }
        }
    }catch(Exception e){
        System.out.println(e.toString());
    }
    return "("+hasil+")";
    }
    
    public String getIsiTabel(String[] Isinya){
        String hasil="";
        int deteksi = Isinya.length-1;
        
        try{
            for(int i = 0; i <Isinya.length;i++){
            if(i==deteksi){
                hasil = hasil+"'"+Isinya[i]+"'";
            }else{
                hasil = hasil+"'"+Isinya[i]+"',";
            
                }
            
            }
        
        }catch(Exception e){
            System.out.println(e.toString());
        }
    
    return "("+hasil+")";
    }
    
    public String getEditFieldValue(String[] Field, String[] ValueField){
    
        String hasil ="";
        int deteksi = Field.length-1;
        try{
            for (int i = 0 ; i < Field.length; i++){
            if(i == deteksi){
                hasil = hasil +Field[i]+"='"+ValueField[i]+"'";
            
            }else{
            hasil = hasil +Field[i]+"='"+ValueField[i]+"', ";
            
            }
        
        }
        
        }catch(Exception e){
            System.out.println(e.toString());
        
        }
        return hasil;
    }
    
    public void UbahDBAuto(String NamaTabel, String NamaPrimary, String IsiPrimary, String[]Field, String[]ValueField){
        
        try{
        String SQLEdit = "UPDATE "+NamaTabel+" SET "+getEditFieldValue(Field, ValueField)+" WHERE "+NamaPrimary+"='"+IsiPrimary+"'";
        Statement perintah = getkoneksi().createStatement();
        perintah.executeUpdate(SQLEdit);
        getkoneksi().close();
        perintah.close();
            System.out.println("Data Berhasil Diubah");
        }catch(Exception e){
        
            System.out.println(e.toString());
        }
    
    
    }
    
    public void SimpanDBAuto(String NamaTabel, String[] FieldTablenya, String[] IsiTabelnya){ //Program SIMPAN Secara Dinamis//
    try{
        String SQL ="INSERT INTO "+NamaTabel+" "+getFieldTabel(FieldTablenya)+" VALUES "+getIsiTabel(IsiTabelnya);
        Statement save = getkoneksi().createStatement();
        save.executeUpdate(SQL);
        save.close();
        System.out.println("Data Berhasil Disimpan");
    }catch(Exception e){
        System.err.println(e.toString());
    
    }
        
    }
    
    public void HapusDBAuto( String NamaTabel, String Primarynya, String IsiPrimary){
    
        try{
            String SQLHapus = "DELETE FROM "+NamaTabel+" WHERE "+Primarynya+"='"+IsiPrimary+"'";
            Statement perintah = getkoneksi().createStatement();
            perintah.executeUpdate(SQLHapus);
            perintah.close();
            getkoneksi().close();
            System.out.println("Data Berhasil Dihapus");
    
        }catch(Exception e){
            System.out.println(e.toString());
        }
    
    }
  
    public void JudulJtable(JTable JTablenya, String[] Judulnya){
        DefaultTableModel modelnya = new DefaultTableModel();
        try{
        JTablenya.setModel(modelnya);
        for (int i = 0; i < Judulnya.length; i++){
            modelnya.addColumn(Judulnya[i]);
        
        }
        }catch(Exception e){
            System.out.println(e.toString());
        }
    
    
    }
    
    public void LebarJTable(JTable JTablenya, int[] Lebarnya){
        
        TableColumn kolom = new TableColumn();
        try{
        JTablenya.setAutoResizeMode(javax.swing.JTable.AUTO_RESIZE_OFF);
        for (int i = 0; i < Lebarnya.length; i++){
            kolom = JTablenya.getColumnModel().getColumn(i);
            kolom.setPreferredWidth(Lebarnya[i]);
        
        }
        
        }catch(Exception e){
            System.out.println(e.toString());
        }
    
    }
    
    
   public void isiDataJTable(JTable Tablenya, String SQL, String[] Fieldnya){
        
        DefaultTableModel modelnya = new DefaultTableModel();
        try{
        Tablenya.setModel(modelnya);
        modelnya.getDataVector().removeAllElements();
        modelnya.fireTableDataChanged();
        
        Statement perintah = getkoneksi().createStatement();
        ResultSet hasil = perintah.executeQuery(SQL);
        
        while(hasil.next()){
            Object[] ob = new Object[Fieldnya.length];
            for(int i = 0; i < Fieldnya.length; i++){
            ob[i] = hasil.getString(Fieldnya[i]);
            modelnya.addRow(ob);
            }
        }
        
        }catch(Exception e){
            JOptionPane.showMessageDialog(null, e.toString());
            System.out.println(e.toString());
        
        }
        
    }
    
    
    public Object[][] isiTabel(String SQL, int jumlah){

    Object[][] data=null;
        try {

        Statement st = getkoneksi().createStatement();
        ResultSet rs = st.executeQuery(SQL);
        rs.last();
            int baris=rs.getRow();
                rs.beforeFirst();
            int j=0;
                data = new Object[baris][jumlah];
        while (rs.next()) {
            for (int i = 0; i < jumlah; i++){
                data[j][i]=rs.getString(i+1);
            }
                j++;
            }
            } catch (Exception e) {
                JOptionPane.showMessageDialog(null,e.toString());
            }
            return data;
        }

    public void tampilTabel(String Judul[],String SQL, JTable Tabel){
        try {
            String title[]=Judul;
            int jum =title.length;
            Tabel.setModel(new DefaultTableModel(isiTabel(SQL,jum), title));
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e.toString());  
        }
    }
    
    public int jumlahRecord(String SQL){
        int hasil=0;
        int i=0;
            try {
                Statement st=getkoneksi().createStatement();
                ResultSet rs = st.executeQuery(SQL);
            while (rs.next()){
                i++;
            }
        hasil=i;
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null,e.toString());
        }
            return hasil;
    }
}
