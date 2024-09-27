/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.dao.impl;

import com.constant.Constant;
import com.dao.BatchDao;
import com.database.Database;
import com.model.Batch;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Ganesh
 */
public class BatchDaoImpl implements BatchDao, Constant{

    @Override
    public boolean isBatch(String batchName) {
        boolean isValid = false;
        try {
            String sql = "SELECT * FROM "+TABLE_NAME_BATCH+" WHERE "+
                            BATCH_NAME+"=?";
            PreparedStatement statement = Database.getConnction().prepareStatement(sql);
            statement.setString(1, batchName);
            ResultSet result = statement.executeQuery();
            while(result.next()){
                if(batchName.equals(result.getString(batchName))){
                    isValid = true;
                    break;
                }
            }
                    
        } catch (SQLException e) {
            e.printStackTrace(System.err);
        }
        return isValid;
    }

    @Override
    public boolean insertBatch(Batch batch) {
        boolean isValid = false;
        try {
            String sql = "INSERT INTO "+TABLE_NAME_BATCH+" ("+
                      BATCH_ID+","+BATCH_NAME+","+BATCH_ADD_DATE+","+STATUS+") "+
                      " VALUES (?,?,?,?)";
            PreparedStatement statement = Database.getConnction().prepareStatement(sql);
            statement.setInt(1, batch.getBatchID());
            statement.setString(2, batch.getBatchName());
            statement.setString(3, batch.getBatchAddDate());
            statement.setInt(4 , 1);
            int result = statement.executeUpdate();
            if(1 == result){
                isValid = true;
            }
        } catch (SQLException e) {
            e.printStackTrace(System.err);
        }
        return isValid;
    }

    @Override
    public List<Batch> getBatches() {
        List<Batch> batches = new ArrayList<>();
        try {
          String sql = "SELECT * FROM "+TABLE_NAME_BATCH;
          PreparedStatement statement = Database.getConnction().prepareStatement(sql);
          ResultSet result = statement.executeQuery();
          while(result.next()){
              Batch batch = new Batch();
              batch.setBatchID(result.getInt(BATCH_ID));
              batch.setBatchName(result.getString(BATCH_NAME));
              batch.setBatchAddDate(result.getString(BATCH_ADD_DATE));
              batch.setStatus(result.getString(STATUS));
              batches.add(batch);
          }
        } catch (SQLException e) {
            e.printStackTrace(System.err);
        }
        return batches;
    }

    @Override
    public String getBatchName(int batchId) {
        String batchName = "";
        try {
            String sql = "SELECT * FROM "+TABLE_NAME_BATCH+" WHERE "+BATCH_ID+"=?";
            PreparedStatement statement = Database.getConnction().prepareStatement(sql);
            statement.setInt(1, batchId);
            ResultSet result = statement.executeQuery();
            while(result.next()){
                if(batchId == result.getInt(BATCH_ID)){
                    batchName = result.getString(BATCH_NAME);
                    
                }
            }
        } catch (SQLException e) {
            e.printStackTrace(System.err);
        }
        return batchName;
    }
    
}
