package deim.urv.cat.homework2.controller;

import deim.urv.cat.homework2.model.AlertMessage;
import deim.urv.cat.homework2.model.SignUpAttempts;
import deim.urv.cat.homework2.service.UserService;

import jakarta.inject.Inject;
import jakarta.mvc.Controller;
import jakarta.mvc.UriRef;
import jakarta.mvc.binding.BindingResult;
import jakarta.mvc.binding.ParamError;
import jakarta.mvc.security.CsrfProtected;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;
import jakarta.ws.rs.BeanParam;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.core.Context;
import java.util.Base64;


@Controller
@Path("LogIn")
public class LogInFormController {    
    // CDI
    @Inject BindingResult bindingResult;
    @Inject UserForm user;
    @Inject UserService service;
    @Inject SignUpAttempts attempts;
    
    @Context
    private HttpServletRequest request;
    
    @GET
    @UriRef("show-log-in")
    public String showForm() {
        return "login-form.jsp"; // Injects CRSF token
    }    
    
    @POST
    @UriRef("log-in")
    @CsrfProtected
    public String logIn(@Valid @BeanParam UserForm userForm) {
        int id;
        HttpSession session = request.getSession();
        
        if (bindingResult.isFailed()) {
            AlertMessage alert = AlertMessage.danger("Validation failed!");
            bindingResult.getAllErrors()
                    .stream()
                    .forEach((ParamError t) -> {
                        alert.addError(t.getParamName(), "", t.getMessage());
                    });
            session.setAttribute("errors", alert);
            return "login-form.jsp";
        }
        
        if(attempts.hasExceededMaxAttempts()) {
            return "login-form.jsp";
        }
        
        id = service.verifyUser(user);
        if(id == 0) {
            AlertMessage verifyAlert = AlertMessage.warning("Usuari i/o contrasenya incorrectes");
            verifyAlert.addError("Error", "", "Usuari i/o contrasenya incorrectes");
            session.setAttribute("errors", verifyAlert);
            return "login-form.jsp";
        }
        
        String valueToEncode = userForm.getUsername() + ":" + userForm.getPassword();
        String valueEncoded = "Basic " + Base64.getEncoder().encodeToString(valueToEncode.getBytes());
        session.setAttribute("credentials", valueEncoded);

        userForm.setId(id);
        session.setAttribute("user", userForm);
        return "redirect:/GamesList";
    } 
}
