package action;

import ATM.ATM;
import ATMDepartment.ATMDepartment;

/**
 * Created by Vadoniy on 06.01.2018.
 */
public class ATMDepartmentMain {

    public static void main(String[] args) {

        ATMDepartment dep1 = new ATMDepartment();

//        Deliver ATMs to points:
        ATM atm0 = new ATM(dep1.fillCells());
        ATM atm1 = new ATM(dep1.fillCells(10,103,25,28,45,60));
        ATM atm2 = new ATM(dep1.fillCells(0,10,10,20,31,3));
        ATM atm3 = new ATM(dep1.fillCells());

//        Aggregate ATMs to department:
        dep1.addObserver(atm0);
        dep1.addObserver(atm1);
        dep1.addObserver(atm2);
        dep1.addObserver(atm3);

//        Check current state of all ATMs in department:
        dep1.notifyObserver("initial");

//        Work with ATM and remove one of them:
        atm1.interaction(atm1);
        dep1.removeObserver(atm2);

//        Check, was state updated or not
        dep1.notifyObserver("final");

//        Recover initial state of all ATMs
        dep1.notifyObserver("reload");

//        Check, is initial state or not
        dep1.notifyObserver("final");
    }
}
