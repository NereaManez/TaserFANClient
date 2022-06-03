package es.ieslavereda.taserfan.base;

import es.ieslavereda.taserfan.entity.Employee;

public class LoggedInUserRepository {
    private static LoggedInUserRepository loggedInUserRepository;
    private static Employee employee;

    public static LoggedInUserRepository getInstance() {
        if (loggedInUserRepository == null)
            loggedInUserRepository = new LoggedInUserRepository();
        return loggedInUserRepository;
    }

    public void login(Employee employee) {
        LoggedInUserRepository.employee = employee;
    }

    public Employee getLoggedInUser() {
        return employee;
    }

    public void logout() {
        employee = null;
    }
}
