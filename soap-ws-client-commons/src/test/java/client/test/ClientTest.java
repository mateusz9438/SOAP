package client.test;

import org.assertj.core.api.Assertions;
import org.junit.BeforeClass;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.oxm.jaxb.Jaxb2Marshaller;

import countries.core.common.AddCountryResponse;
import countries.core.common.Currency;
import countries.core.common.DeleteCountryResponse;
import countries.core.common.GetCountryResponse;
import countries.core.common.LoginUserResponse;
import countries.core.common.LogoutUserResponse;
import hello.CountriesClient;


/**
 * Created by Mateusz on 2017-06-10.
 */
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class ClientTest {

    private static final Logger log = LoggerFactory.getLogger(ClientTest.class);
    private static Jaxb2Marshaller marshaller;
    private static CountriesClient countriesClient;
    private static String uuid;


    @BeforeClass
    public static void setUp() throws Exception {
        marshaller = new Jaxb2Marshaller();
        marshaller.setContextPath("countries.core.common");
        marshaller.afterPropertiesSet();
        countriesClient = new CountriesClient(marshaller);
    }

    @Test
    public void test1_getCountryFailedTest() {
        // when
        GetCountryResponse getCountryResponse = countriesClient.getCountry("Russia");
        // then
        Assertions.assertThat(getCountryResponse.getCountry()).isNull();
    }

    @Test
    public void test2_loginUserFailedTest() {
        // when
        LoginUserResponse loginUserResponse = countriesClient.loginUser("fake", "fake");
        uuid = loginUserResponse.getUuid();
        // then
        Assertions.assertThat(loginUserResponse.getMessage()).isEqualTo("Login failed");
    }
    @Test
    public void test3_loginUserSuccessTest() {
        // when
        LoginUserResponse loginUserResponse = countriesClient.loginUser("user1", "user1");
        uuid = loginUserResponse.getUuid();
        // then
        Assertions.assertThat(loginUserResponse.getMessage()).isEqualTo("Login successful");
    }
    @Test
    public void test4_addCountrySuccessTest(){
        // given
        String name = "Germany";
        String capital = "Berlin";
        int population = 123456789;
        Currency currency = Currency.EUR;

        // when
        AddCountryResponse addCountryResponse = countriesClient.addCountry(uuid,name,capital,currency,population);
        // then
        Assertions.assertThat(addCountryResponse.getMessage()).isEqualTo("Country added");
    }
    @Test
    public void test5_addCountryFailedTest(){
        // given
        String fakeUuid = "123456789";
        String name = "Russia";
        String capital = "Moscow";
        int population = 123456789;
        Currency currency = Currency.PLN;

        // when
        AddCountryResponse addCountryResponse = countriesClient.addCountry(fakeUuid,name,capital,currency,population);
        // then
        Assertions.assertThat(addCountryResponse.getMessage()).isEqualTo("Access denied");
    }
    @Test
    public void test6_getCountrySuccessTest() {
        // when
        GetCountryResponse getCountryResponse = countriesClient.getCountry("Germany");

        // then
        Assertions.assertThat(getCountryResponse.getCountry().getName()).isEqualTo("Germany");
        Assertions.assertThat(getCountryResponse.getCountry().getCapital()).isEqualTo("Berlin");
        Assertions.assertThat(getCountryResponse.getCountry().getCurrency()).isEqualTo(Currency.EUR);
        Assertions.assertThat(getCountryResponse.getCountry().getPopulation()).isEqualTo(123456789);
    }
    @Test
    public void test7_deleteCountrySuccessTest(){
        // when
        DeleteCountryResponse deleteCountryResponse = countriesClient.deleteCountry(uuid,"Germany");

        // then
        Assertions.assertThat(deleteCountryResponse.getMessage()).isEqualTo("Country removed");
    }
    @Test
    public void test8_deleteCountryFailedTest(){
        // given
        String fakeUuid = "123456789";
        // when
        DeleteCountryResponse deleteCountryResponse = countriesClient.deleteCountry(fakeUuid,"Germany");

        // then
        Assertions.assertThat(deleteCountryResponse.getMessage()).isEqualTo("Access denied");
    }
    @Test
    public void test9_logoutSuccess(){
        //when
        LogoutUserResponse logoutUserResponse = countriesClient.logoutUser(uuid,"user1");

        //then
        Assertions.assertThat(logoutUserResponse.getMessage()).isEqualTo("user1 logout successful");
    }
    @Test
    public void test9_logoutFailedLoginIncorrect(){
        //when
        LogoutUserResponse logoutUserResponse = countriesClient.logoutUser(uuid,"fake");

        //then
        Assertions.assertThat(logoutUserResponse.getMessage()).isEqualTo("you are not logged");
    }
    @Test
    public void test9_logoutFailedUuidIncorrect(){
        //when
        LogoutUserResponse logoutUserResponse = countriesClient.logoutUser("123456789","user1");

        //then
        Assertions.assertThat(logoutUserResponse.getMessage()).isEqualTo("you are not logged");
    }


}
