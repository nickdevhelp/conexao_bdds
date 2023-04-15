
package control;

import conexao.Conexao;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.Statement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.ParseException;

import java.text.SimpleDateFormat;
import java.time.LocalDate;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

/*
    tabela dadosUsuarios

    CREATE TABLE dadosUsuarios(
	codigo INT NOT NULL,
        nome VARCHAR(45) NOT NULL,
        data_nascimento VARCHAR(12) NOT NULL,
        telefone VARCHAR(45) NOT NULL,
        email VARCHAR(45),
        PRIMARY KEY(codigo)
    );
*/


public class FormularioPrincipal extends javax.swing.JFrame {
    java.sql.Date d = null;
    
    Connection conexao = null;
    PreparedStatement ptsm = null;
    ResultSet result = null;
   
    public FormularioPrincipal() {
        initComponents();
        conexao = Conexao.conectar();
        
        if(conexao != null){
            tblStatus.setText("conectado");
            executeSQL("select * from dadosUsuarios order by codigo");
            puxarDados();
        }
        else{
            tblStatus.setText("não conectado");
                    
        }
        
    }
    
    
       
    

    
    public void posicionarDados(){
        try{
            result.first();
            exibirDados();
        }catch(SQLException e){
            JOptionPane.showMessageDialog(null,"Não foi possível posicionar no primeiro registro:"+e,
                    "Mensagem do Programa",JOptionPane.INFORMATION_MESSAGE);
        }
    }
    
    public void exibirDados() {
        try{
            txtCodigo.setText(result.getString("codigo"));
            txtNome.setText(result.getString("nome"));
            txtNascimento.setText(result.getString("data_nascimento"));
            txtTelefone.setText(result.getString("telefone"));
            txtEmail.setText(result.getString("email"));
        }
        catch(SQLException e){
            JOptionPane.showMessageDialog(null,"Não localizou dados: "+e,
                    "Mensagem doPrograma",JOptionPane.INFORMATION_MESSAGE);

        }
    }
    
    public void executeSQL(String sql){
         try{
            ptsm = conexao.prepareStatement(sql);
            result = ptsm.executeQuery();
            result.first();
            
        }catch(SQLException e){
            JOptionPane.showMessageDialog(null, e);
        }
    }
    
    public void executaSQL2(String sql){
        try{
            Statement stm = null;
            stm = conexao.createStatement(result.TYPE_SCROLL_INSENSITIVE, result.CONCUR_READ_ONLY);
            result = stm.executeQuery(sql);
            
        }catch(SQLException e){
            JOptionPane.showMessageDialog(null, "Erro no comando SQL: "+e, "Mensagem do programa", JOptionPane.INFORMATION_MESSAGE);
        }
    }
    
    public void updateSQL(String sql){
        try{
            ptsm = conexao.prepareStatement(sql);
            ptsm.executeUpdate(sql);
            JOptionPane.showMessageDialog(null, "Gravação realizada com sucesso", "Mensagem do programa", JOptionPane.INFORMATION_MESSAGE);
            
        }catch(SQLException e){
            JOptionPane.showMessageDialog(null, "\n Erro na gravação: \n"+e, "mensagem do programa", JOptionPane.INFORMATION_MESSAGE);
        
        }
    }
    
    public void insertSQL(String sql,String codigo, String nome, String data_nasc, String telefone, String email){
        try{
            
            ptsm.setString(1, codigo);
            ptsm.setString(2, nome);
            ptsm.setString(3, data_nasc);
            ptsm.setString(4, telefone);
            ptsm.setString(5, email);
            
            ptsm = conexao.prepareStatement(sql);
            ptsm.executeUpdate(sql);
            JOptionPane.showMessageDialog(null, "Gravação realizada com sucesso", "Mensagem do programa", JOptionPane.INFORMATION_MESSAGE);
            
        }catch(SQLException e){
            JOptionPane.showMessageDialog(null, "\n Erro na gravação: \n"+e, "mensagem do programa", JOptionPane.INFORMATION_MESSAGE);
        
        }
    }
    
    public void puxarDados(){
        
        tblclientes.getColumnModel().getColumn(0).setPreferredWidth(4);
        tblclientes.getColumnModel().getColumn(1).setPreferredWidth(150);
        tblclientes.getColumnModel().getColumn(2).setPreferredWidth(11);
        tblclientes.getColumnModel().getColumn(3).setPreferredWidth(14);
        tblclientes.getColumnModel().getColumn(4).setPreferredWidth(100);
        
        DefaultTableModel modelo = (DefaultTableModel) tblclientes.getModel();
        modelo.setNumRows(0);
        
        
        try{
            result.beforeFirst();
            while(result.next()){
                modelo.addRow(new Object[]{
                         result.getString("codigo"),result.getString("nome"),
                         result.getString("data_nascimento"),result.getString("telefone"),result.getString("email")

            });
            }
        }catch(SQLException erro){
             JOptionPane.showMessageDialog(null,"\n Erro ao listar dados da tabela!! :\n "+erro,
                     "Mensagem do Programa",JOptionPane.INFORMATION_MESSAGE);
        }
    }
    
    
    
    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        txtCodigo = new javax.swing.JTextField();
        txtNome = new javax.swing.JTextField();
        txtNascimento = new javax.swing.JTextField();
        txtTelefone = new javax.swing.JTextField();
        txtEmail = new javax.swing.JTextField();
        jScrollPane1 = new javax.swing.JScrollPane();
        tblclientes = new javax.swing.JTable();
        tblStatus = new javax.swing.JLabel();
        buttBack = new javax.swing.JButton();
        buttPlay = new javax.swing.JButton();
        buttRevPlay = new javax.swing.JButton();
        buttNext = new javax.swing.JButton();
        jLabel6 = new javax.swing.JLabel();
        txtPesquisaUsuario = new javax.swing.JTextField();
        buttNovo = new javax.swing.JButton();
        buttCadastrar = new javax.swing.JButton();
        buttExcuir = new javax.swing.JButton();
        buttAlterar = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setMaximumSize(new java.awt.Dimension(726, 381));
        setMinimumSize(new java.awt.Dimension(726, 381));
        setPreferredSize(new java.awt.Dimension(726, 381));
        setResizable(false);

        jLabel1.setText("Código");

        jLabel2.setText("Nome");

        jLabel3.setText("Data Nascimento");

        jLabel4.setText("Telefone");

        jLabel5.setText("Email");

        txtCodigo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtCodigoActionPerformed(evt);
            }
        });

        tblclientes.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null}
            },
            new String [] {
                "Código", "Nome", "Data Nascimento", "Telefone", "Email"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tblclientes.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tblclientesMouseClicked(evt);
            }
            public void mousePressed(java.awt.event.MouseEvent evt) {
                tblclientesMousePressed(evt);
            }
        });
        jScrollPane1.setViewportView(tblclientes);

        tblStatus.setText("Status");

        buttBack.setBackground(new java.awt.Color(153, 255, 255));
        buttBack.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        buttBack.setText("<| |");
        buttBack.setMaximumSize(new java.awt.Dimension(47, 25));
        buttBack.setMinimumSize(new java.awt.Dimension(47, 25));
        buttBack.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                buttBackActionPerformed(evt);
            }
        });

        buttPlay.setBackground(new java.awt.Color(153, 255, 255));
        buttPlay.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        buttPlay.setText("►");
        buttPlay.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                buttPlayActionPerformed(evt);
            }
        });

        buttRevPlay.setBackground(new java.awt.Color(153, 255, 255));
        buttRevPlay.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        buttRevPlay.setText("◄");
        buttRevPlay.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                buttRevPlayActionPerformed(evt);
            }
        });

        buttNext.setBackground(new java.awt.Color(153, 255, 255));
        buttNext.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        buttNext.setText(">| |");
        buttNext.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                buttNextActionPerformed(evt);
            }
        });

        jLabel6.setText("Pesquisa por nome do Cliente");

        txtPesquisaUsuario.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtPesquisaUsuarioActionPerformed(evt);
            }
        });
        txtPesquisaUsuario.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtPesquisaUsuarioKeyReleased(evt);
            }
        });

        buttNovo.setBackground(new java.awt.Color(153, 255, 153));
        buttNovo.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        buttNovo.setText("+");
        buttNovo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                buttNovoActionPerformed(evt);
            }
        });

        buttCadastrar.setBackground(new java.awt.Color(153, 255, 153));
        buttCadastrar.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        buttCadastrar.setText("@");
        buttCadastrar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                buttCadastrarActionPerformed(evt);
            }
        });

        buttExcuir.setBackground(new java.awt.Color(153, 255, 153));
        buttExcuir.setText("⌧");
        buttExcuir.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                buttExcuirActionPerformed(evt);
            }
        });

        buttAlterar.setBackground(new java.awt.Color(153, 255, 153));
        buttAlterar.setText("✎");
        buttAlterar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                buttAlterarActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(171, 171, 171)
                .addComponent(jLabel6)
                .addGap(18, 18, 18)
                .addComponent(txtPesquisaUsuario, javax.swing.GroupLayout.PREFERRED_SIZE, 251, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
            .addGroup(layout.createSequentialGroup()
                .addContainerGap(61, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 714, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jLabel1)
                            .addComponent(jLabel2)
                            .addComponent(jLabel4)
                            .addComponent(jLabel5)
                            .addComponent(jLabel3))
                        .addGap(39, 39, 39)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(txtNome, javax.swing.GroupLayout.PREFERRED_SIZE, 199, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(txtCodigo, javax.swing.GroupLayout.PREFERRED_SIZE, 68, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 349, Short.MAX_VALUE)
                                .addComponent(tblStatus)))
                        .addGap(185, 185, 185))
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(buttBack, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(layout.createSequentialGroup()
                                .addGap(78, 78, 78)
                                .addComponent(buttPlay)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(txtTelefone, javax.swing.GroupLayout.PREFERRED_SIZE, 199, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(txtNascimento, javax.swing.GroupLayout.PREFERRED_SIZE, 199, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(txtEmail, javax.swing.GroupLayout.PREFERRED_SIZE, 199, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addGroup(layout.createSequentialGroup()
                                        .addGap(10, 10, 10)
                                        .addComponent(buttRevPlay)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                        .addComponent(buttNext)
                                        .addGap(152, 152, 152)
                                        .addComponent(buttNovo)
                                        .addGap(18, 18, 18)
                                        .addComponent(buttCadastrar)
                                        .addGap(18, 18, 18)
                                        .addComponent(buttAlterar)))))
                        .addGap(18, 18, 18)
                        .addComponent(buttExcuir)
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(39, 39, 39)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtCodigo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel1)
                    .addComponent(tblStatus))
                .addGap(25, 25, 25)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(txtNome, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(22, 22, 22)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3)
                    .addComponent(txtNascimento, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(26, 26, 26)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel4)
                    .addComponent(txtTelefone, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel5)
                    .addComponent(txtEmail, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(buttBack, javax.swing.GroupLayout.DEFAULT_SIZE, 57, Short.MAX_VALUE)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(buttPlay)
                        .addComponent(buttRevPlay, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(buttNext)
                        .addComponent(buttNovo)
                        .addComponent(buttCadastrar)
                        .addComponent(buttAlterar)
                        .addComponent(buttExcuir)))
                .addGap(33, 33, 33)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 181, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(40, 40, 40)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel6)
                    .addComponent(txtPesquisaUsuario, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(50, 50, 50))
        );

        setSize(new java.awt.Dimension(841, 649));
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void txtCodigoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtCodigoActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtCodigoActionPerformed

    private void tblclientesMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblclientesMouseClicked
       int linha_selecionada = tblclientes.getSelectedRow();
       
        txtCodigo.setText(tblclientes.getValueAt(linha_selecionada, 0).toString());
        txtNome.setText(tblclientes.getValueAt(linha_selecionada, 1).toString());
        txtNascimento.setText(tblclientes.getValueAt(linha_selecionada, 2).toString());
        txtTelefone.setText(tblclientes.getValueAt(linha_selecionada, 3).toString());
        txtEmail.setText(tblclientes.getValueAt(linha_selecionada, 4).toString());

    }//GEN-LAST:event_tblclientesMouseClicked

    private void tblclientesMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblclientesMousePressed
        int linha_selecionada = tblclientes.getSelectedRow();
       
        txtCodigo.setText(tblclientes.getValueAt(linha_selecionada, 0).toString());
        txtNome.setText(tblclientes.getValueAt(linha_selecionada, 1).toString());
        txtNascimento.setText(tblclientes.getValueAt(linha_selecionada, 2).toString());
        txtTelefone.setText(tblclientes.getValueAt(linha_selecionada, 3).toString());
        txtEmail.setText(tblclientes.getValueAt(linha_selecionada, 4).toString());

    }//GEN-LAST:event_tblclientesMousePressed

    private void buttBackActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buttBackActionPerformed
        try{
            result.first();
            exibirDados();
            
        }catch(SQLException e){
            JOptionPane.showMessageDialog(null, "Não foi possivel acesasr o primeiro registro"+e,"mensagem do programa", JOptionPane.INFORMATION_MESSAGE);
        }    
    }//GEN-LAST:event_buttBackActionPerformed

    private void buttPlayActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buttPlayActionPerformed
        try{
            result.first();
            exibirDados();
            
        }catch(SQLException e){
            JOptionPane.showMessageDialog(null, "Não foi possivel acesasr o primeiro registro"+e,"mensagem do programa", JOptionPane.INFORMATION_MESSAGE);
        }    
    }//GEN-LAST:event_buttPlayActionPerformed

    private void buttRevPlayActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buttRevPlayActionPerformed
         try{
            result.first();
            exibirDados();
            
        }catch(SQLException e){
            JOptionPane.showMessageDialog(null, "Não foi possivel acesasr o primeiro registro"+e,"mensagem do programa", JOptionPane.INFORMATION_MESSAGE);
        }    
    }//GEN-LAST:event_buttRevPlayActionPerformed

    private void buttNextActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buttNextActionPerformed
         try{
            result.first();
            exibirDados();
            
        }catch(SQLException e){
            JOptionPane.showMessageDialog(null, "Não foi possivel acesasr o primeiro registro"+e,"mensagem do programa", JOptionPane.INFORMATION_MESSAGE);
        }    
    }//GEN-LAST:event_buttNextActionPerformed

    private void buttCadastrarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buttCadastrarActionPerformed
        String codigo = txtCodigo.getText();
        String nome = txtNome.getText();
        String data_nasc = txtNascimento.getText();
        String telefone = txtTelefone.getText();
        String email = txtEmail.getText();
        try{
           String sql = "insert into dadosUsuarios (codigo, nome, data_nascimento, telefone, email) values ('" + codigo + "','" + nome + "','" + data_nasc + "','" + telefone + "','" + email + "');";
             
             ptsm.executeUpdate(sql);
             
             executeSQL("select * from dadosUsuarios order by codigo;");
             puxarDados();
            
            
            result.first();
            exibirDados();
            
        }catch(SQLException e){
            JOptionPane.showMessageDialog(null, "Não há nenhum dado nos campo para ser adicionado","mensagem do programa", JOptionPane.INFORMATION_MESSAGE);
        }    
        
            
    }//GEN-LAST:event_buttCadastrarActionPerformed

    private void buttNovoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buttNovoActionPerformed
        txtCodigo.setText("");
        txtNome.setText("");
        txtNascimento.setText("");
        txtTelefone.setText("");
        txtEmail.setText("");
        txtCodigo.requestFocus();
    }//GEN-LAST:event_buttNovoActionPerformed

    private void buttAlterarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buttAlterarActionPerformed
        
        String codigoAlter = JOptionPane.showInputDialog("Informe o codigo da tabela a ser alterada: ");
        
        String codigo = txtCodigo.getText();
        String nome = txtNome.getText();
        String data_nasc = txtNascimento.getText();
        String telefone = txtTelefone.getText();
        String email = txtEmail.getText();
        String sql = "";
        String msg = "";
        
       
        
        try{
            if(txtCodigo.equals("")){
            sql = "insert into dadosUsuarios (codigo, nome, data_nascimento, telefone, email) values ('" + codigo + "','" + nome + "','" + telefone + "','" + email + "','" + data_nasc + "');";
            msg = "Gravação realizada com sucesso";
            
        
        }
        else{
            sql = String.format("UPDATE dadosUsuarios SET  nome = '%s', data_nascimento = '%s', telefone = '%s', email = '%s' WHERE codigo = %s;",
                            nome, data_nasc, email, telefone, codigoAlter);
            msg = "alteração de registro";
            
        }
        
        ptsm.executeUpdate(sql);
        
        executeSQL("select * from dadosUsuarios order by codigo;");
        puxarDados();
        exibirDados();
        
        }catch(SQLException e){
            JOptionPane.showMessageDialog(null, "\n Erro na gravação: \n"+e, "mensagem do programa", JOptionPane.INFORMATION_MESSAGE);
        
        }
        
        
    }//GEN-LAST:event_buttAlterarActionPerformed

    private void buttExcuirActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buttExcuirActionPerformed
        
       
        
        
       try{
            int resposta = JOptionPane.showConfirmDialog(rootPane, "Deseja excluir o registro: ", "Confirmar", JOptionPane.YES_NO_OPTION, 3);
            
            String codExcluir = JOptionPane.showInputDialog("Insira o codigo da coluna a ser excluida");
        
            if(resposta == 0){
                String sql = "delete from dadosUsuarios where codigo = " + codExcluir;
                ptsm.executeUpdate(sql);


                JOptionPane.showMessageDialog(null, "Exclusão realizada com sucesso!!", "mensagem do programa", JOptionPane.INFORMATION_MESSAGE);
                executeSQL("select * from dadosUsuarios order by codigo");
                puxarDados();
                exibirDados();
            
            }
            else{
                JOptionPane.showMessageDialog(null, "Operação cancelada pelo usuário!", "mensagem do programa", JOptionPane.INFORMATION_MESSAGE);
                }
            
        
       }catch(SQLException e){
           JOptionPane.showMessageDialog(null, "Erro na exclusão: "+e, "mensagem do programa", JOptionPane.INFORMATION_MESSAGE);
        
       }
            
    }//GEN-LAST:event_buttExcuirActionPerformed

    private void txtPesquisaUsuarioKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtPesquisaUsuarioKeyReleased
        if(evt.getKeyCode() == java.awt.event.KeyEvent.VK_ENTER){
            
            
            
            try{
               

                if(result.first()){

                    try{
                        DefaultTableModel modelo = (DefaultTableModel) tblclientes.getModel();
                        modelo.setRowCount(0);
                        tblclientes.repaint();
                        String select = "select * from dadosUsuarios where nome like '" + txtPesquisaUsuario.getText() + "%'";
                        executaSQL2(select);
                        puxarDados();
                    }
                    catch(Exception e){
                         JOptionPane.showMessageDialog(null, "Erro identificado"+e, "mensagem do programa", JOptionPane.INFORMATION_MESSAGE);
               
                    }

                }
                else{
                    JOptionPane.showMessageDialog(null, "Não existe dados com esses parametros", "mensagem do programa", JOptionPane.INFORMATION_MESSAGE);
                }
        }
            catch(SQLException e){
              JOptionPane.showMessageDialog(null, "Os dados digitados não foram encontrados! "+e, "mensagem do programa", JOptionPane.INFORMATION_MESSAGE);
        
            }
        }
    }//GEN-LAST:event_txtPesquisaUsuarioKeyReleased

    private void txtPesquisaUsuarioActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtPesquisaUsuarioActionPerformed
        
   
        
    }//GEN-LAST:event_txtPesquisaUsuarioActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(FormularioPrincipal.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(FormularioPrincipal.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(FormularioPrincipal.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(FormularioPrincipal.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            @Override
            public void run() {
                new FormularioPrincipal().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton buttAlterar;
    private javax.swing.JButton buttBack;
    private javax.swing.JButton buttCadastrar;
    private javax.swing.JButton buttExcuir;
    private javax.swing.JButton buttNext;
    private javax.swing.JButton buttNovo;
    private javax.swing.JButton buttPlay;
    private javax.swing.JButton buttRevPlay;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JLabel tblStatus;
    private javax.swing.JTable tblclientes;
    private javax.swing.JTextField txtCodigo;
    private javax.swing.JTextField txtEmail;
    private javax.swing.JTextField txtNascimento;
    private javax.swing.JTextField txtNome;
    private javax.swing.JTextField txtPesquisaUsuario;
    private javax.swing.JTextField txtTelefone;
    // End of variables declaration//GEN-END:variables

    
}
