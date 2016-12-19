package ru.stqa.training.selenium.Tests;

import org.junit.Test;

public class cart_add_delete_test extends TestBase {

    @Test
    public void scn_cart_add_delete()
    {
        for(int j=0; j<3; j++) {   //три раза добавим утку:
            app.selectDuck(0);      //выберем утку
            app.addDuckToCart();    //положим в корзину
        }

        app.removeAllFromCart();    //удалим всё из корзины

    }//test

}
