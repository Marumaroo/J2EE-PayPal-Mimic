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
import javax.faces.validator.Validator;
import javax.faces.validator.ValidatorException;

@FacesValidator("amountValidator")
public class AmountValidator implements Validator {

    @Override
    public void validate(FacesContext fc, UIComponent uic, Object t) throws ValidatorException {
        try {
            double n = Double.parseDouble(t.toString());
            if (n < 0) {
                throw new ValidatorException(new FacesMessage(null, "Amount cannot be negative"));
            }
        } catch (NumberFormatException ex) {
            throw new ValidatorException(new FacesMessage(null, "Invalid amount format"));
        }
    }
}
