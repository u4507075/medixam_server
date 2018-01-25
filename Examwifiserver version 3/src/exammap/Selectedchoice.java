/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package exammap;

import java.util.HashMap;

/**
 *
 * @author bon
 */
public class Selectedchoice {
    HashMap<String, String> select = new HashMap<>();
    public void addSelectedchoice(String questionid, String selectedchoiceid)
    {
        select.put(questionid, selectedchoiceid);
    }
    public String getSelectedchoice(String questionid)
    {
        return select.get(questionid);
    }
}
