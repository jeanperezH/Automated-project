package com.tektonlabs.qa.vorwerk.config;

import org.aeonbits.owner.Config;

@Config.Sources("classpath:shop.properties")
public interface ShopConfig extends Config {

    @DefaultValue("stage")
    String env();

    @DefaultValue("us")
    String country();

    @DefaultValue("can")
    String CanCountry();

    @Key("${env}.${country}.domain")
    String domain();

    @Key("${env}.${country}.domain_for_consultant")
    String domainForConsultant();

    @Key("${env}.${country}.environment_username")
    String environmentUsername();

    @Key("${env}.${country}.environment_password")
    String environmentPassword();

    @Key("${env}.${country}.new_consultant_email_address")
    String newConsultantEmailAddress();

    @Key("${env}.${country}.new_consultant_password")
    String newConsultantPassword();

    @Key("${env}.${country}.coupon_code")
    String couponCode();

    @Key("${env}.${country}.email_address")
    String emailAddress();

    @Key("${env}.${country}.password")
    String password();

    @Key("${env}.${country}.guest_email_address")
    String guestEmailAddress();

    @Key("${env}.${country}.guest_password")
    String guestPassword();

    @Key("${env}.${country}.guest_coupon_code")
    String guestCouponCode();

    @Key("${env}.${country}.guest_first_name")
    String guestFirstName();

    @Key("${env}.${country}.guest_last_name")
    String guestLastName();

    @Key("${env}.${country}.first_name")
    String firstName();

    @Key("${env}.${country}.last_name")
    String lastName();

    @Key("${env}.${country}.phone")
    String phone();

    @Key("${env}.${country}.bread_code")
    String breadCode();

    @Key("${env}.${country}.street_address")
    String streetAddress();

    @Key("${env}.${country}.street_address_with_ups")
    String streetAddressWithUps();

    @Key("${env}.${country}.street_address_with_canada_post")
    String streetAddressWithCanPost();

    @Key("${env}.${country}.city")
    String city();

    @Key("${env}.${country}.city_with_bpa_warning")
    String cityWithBpaWarning();

    @Key("${env}.${country}.city_with_ups")
    String cityWithUps();

    @Key("${env}.${country}.city_with_canada_post")
    String cityWithCanPost();

    @Key("${env}.${country}.billing_state")
    String billingState();

    @Key("${env}.${country}.billing_state_with_bpa_warning")
    String billingStateWithBpaWarning();

    @Key("${env}.${country}.billing_state_with_ups")
    String billingStateWithUps();

    @Key("${env}.${country}.billing_state_with_canada_post")
    String billingStateWithCanPost();

    @Key("${env}.${country}.zip")
    String zip();

    @Key("${env}.${country}.zip_with_bpa_warning")
    String zipWithBpaWarning();

    @Key("${env}.${country}.zip_with_ups")
    String zipWithUps();

    @Key("${env}.${country}.zip_with_canada_post")
    String zipWithCanPost();

    @Key("${env}.${country}.card_number")
    String cardNumber();

    @Key("${env}.${country}.expiry_date")
    String expiryDate();

    @Key("${env}.${country}.cvc")
    String cvc();

    @Key("${env}.${country}.paypal_email_address")
    String paypalEmailAddress();

    @Key("${env}.${country}.paypal_password")
    String paypalPassword();
}
