package lanaDrahrepus.model;


import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

@Component
public class PhoneNumber implements Validator {

    private String phone;

    public PhoneNumber() {
    }

    public PhoneNumber(String phone) {
        this.phone = phone;
    }

    public void setPhone(String phoneNumber) {
        this.phone = phoneNumber;
    }

    public String getPhone() {
        return this.phone;
    }

    @Override
    public boolean supports(Class<?> cls) {
        return PhoneNumber.class.isAssignableFrom(cls);
    }

    @Override
    public void validate(Object target, Errors errors) {
        PhoneNumber phoneNumber = (PhoneNumber) target;
        String number = phoneNumber.getPhone();
        ValidationUtils.rejectIfEmpty(errors, "phone", "phone.empty");
        if (number.length() > 11) {
            errors.rejectValue("phone", "phone.tooLong");
        }
        if (number.length() < 10) {
            errors.rejectValue("phone", "phone.tooShort");
        }
        if (!number.startsWith("0")) {
            errors.rejectValue("phone", "phone.startWith0");
        }
        if (!number.matches("^[0-9]*$")) {
            errors.rejectValue("phone", "phone.notNumber");
        }

    }
}
