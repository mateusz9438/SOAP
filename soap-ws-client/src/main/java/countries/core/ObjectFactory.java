
package countries.core;

import javax.xml.bind.annotation.XmlRegistry;


/**
 * This object contains factory methods for each 
 * Java content interface and Java element interface 
 * generated in the countries.core package. 
 * <p>An ObjectFactory allows you to programatically 
 * construct new instances of the Java representation 
 * for XML content. The Java representation of XML 
 * content can consist of schema derived interfaces 
 * and classes representing the binding of schema 
 * type definitions, element declarations and model 
 * groups.  Factory methods for each of these are 
 * provided in this class.
 * 
 */
@XmlRegistry
public class ObjectFactory {


    /**
     * Create a new ObjectFactory that can be used to create new instances of schema derived classes for package: countries.core
     * 
     */
    public ObjectFactory() {
    }

    /**
     * Create an instance of {@link LoginUserResponse }
     * 
     */
    public LoginUserResponse createLoginUserResponse() {
        return new LoginUserResponse();
    }

    /**
     * Create an instance of {@link DeleteCountryRequest }
     * 
     */
    public DeleteCountryRequest createDeleteCountryRequest() {
        return new DeleteCountryRequest();
    }

    /**
     * Create an instance of {@link GetCountryRequest }
     * 
     */
    public GetCountryRequest createGetCountryRequest() {
        return new GetCountryRequest();
    }

    /**
     * Create an instance of {@link DeleteCountryResponse }
     * 
     */
    public DeleteCountryResponse createDeleteCountryResponse() {
        return new DeleteCountryResponse();
    }

    /**
     * Create an instance of {@link LogoutUserResponse }
     * 
     */
    public LogoutUserResponse createLogoutUserResponse() {
        return new LogoutUserResponse();
    }

    /**
     * Create an instance of {@link LogoutUserRequest }
     * 
     */
    public LogoutUserRequest createLogoutUserRequest() {
        return new LogoutUserRequest();
    }

    /**
     * Create an instance of {@link GetCountryResponse }
     * 
     */
    public GetCountryResponse createGetCountryResponse() {
        return new GetCountryResponse();
    }

    /**
     * Create an instance of {@link Country }
     * 
     */
    public Country createCountry() {
        return new Country();
    }

    /**
     * Create an instance of {@link LoginUserRequest }
     * 
     */
    public LoginUserRequest createLoginUserRequest() {
        return new LoginUserRequest();
    }

    /**
     * Create an instance of {@link AddCountryRequest }
     * 
     */
    public AddCountryRequest createAddCountryRequest() {
        return new AddCountryRequest();
    }

    /**
     * Create an instance of {@link AddCountryResponse }
     * 
     */
    public AddCountryResponse createAddCountryResponse() {
        return new AddCountryResponse();
    }

    /**
     * Create an instance of {@link User }
     * 
     */
    public User createUser() {
        return new User();
    }

}
