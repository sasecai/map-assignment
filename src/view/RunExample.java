package view;

import controller.Controller;
import model.containers.MyList;
import model.values.Value;

public class RunExample extends Command{
    private Controller ctr;
    public RunExample(String key, String description, Controller c) {
        super(key, description);
        ctr = c;
    }

    @Override
    public void execute() {
        try{
            ctr.allStep();
            MyList<Value> toPrint = (MyList<Value>) ctr.getOut();
            for(int i = 0; i < toPrint.size(); i ++) {
                System.out.println(toPrint.get(i).toString());
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
}
