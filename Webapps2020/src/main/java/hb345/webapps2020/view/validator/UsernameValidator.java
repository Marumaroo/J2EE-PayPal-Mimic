/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hb345.webapps2020.view.validator;

import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.validator.FacesValidator;
import javax.faces.validator.ValidatorException;
import javax.faces.validator.Validator;

@FacesValidator("usernameValidator")
public class UsernameValidator implements Validator {

    @Override
    public void validate(FacesContext fc, UIComponent uic, Object t) throws ValidatorException {
        if (!t.toString().matches("[a-zA-Z0-9]+")) {
            throw new ValidatorException(new FacesMessage(null, "Username must only contain alphanumerics."));
        } else if (t.toString().length() < 3 || t.toString().length() > 25) {
            throw new ValidatorException(new FacesMessage(null, "Username length must be between 3-25"));
        }
    }
}
