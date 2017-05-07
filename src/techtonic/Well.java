/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package techtonic;

/**
 *
 * @author 1412625
 */
public class Well implements  Comparable<Well>{
    private int id;
    private String name;
    public Well(int id, String op){
        this.id = id;
        this.name = op;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public int compareTo(Well o) {
        return (this.name).compareTo(o.getName());
    }
    @Override
    public String toString(){
     return getName();
    }
}
