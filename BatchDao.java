/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.dao;

import com.model.Batch;
import java.util.List;

/**
 *
 * @author Ganesh
 */
public interface BatchDao {
    public boolean isBatch(String batchName);
    public boolean insertBatch(Batch batch);
    public List<Batch> getBatches();
    public String getBatchName(int batchId);
}
