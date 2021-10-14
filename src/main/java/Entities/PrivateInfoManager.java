package Entities;

import com.sun.source.tree.NewArrayTree;

import java.util.ArrayList;

/**
 * TODO: Write Javadoc
 */
public class PrivateInfoManager {

    /**
     * TODO: Write Javadoc
     */

    private ArrayList<PrivateInfo> info = new ArrayList<PrivateInfo>();

    public PrivateInfoManager(){

    }

    public void AddInfo(PrivateInfo newInfo) {

        this.info.add(newInfo);

    }

    // TODO: everything below this
    // Delete Private Info Here

    public void DeleteInfo(PrivateInfo newInfo) {

        for (PrivateInfo p: this.info){


        }

    }
}
