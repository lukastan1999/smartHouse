
from selenium import webdriver
from selenium.webdriver.common.by import By
from selenium.webdriver.support.ui import WebDriverWait
from selenium.webdriver.support import expected_conditions as EC
import unittest
import time


class RegistrationTest(unittest.TestCase):
    def setUp(self):
        self.driver = webdriver.Chrome()
        self.driver.get("http://localhost:4200/register")
        self.wait = WebDriverWait(self.driver, 10)  # Wait for up to 10 seconds

    def test_registration_form_good(self):
        driver = self.driver

        # Wait for the elements to be present
        name = self.wait.until(EC.presence_of_element_located((By.ID, "name")))
        surname = self.wait.until(EC.presence_of_element_located((By.ID, "surname")))
        email = self.wait.until(EC.presence_of_element_located((By.ID, "email")))
        password = self.wait.until(EC.presence_of_element_located((By.ID, "password")))
        confirm_password = self.wait.until(EC.presence_of_element_located((By.ID, "confirmPassword")))
        address = self.wait.until(EC.presence_of_element_located((By.ID, "address")))
        phone_number = self.wait.until(EC.presence_of_element_located((By.ID, "phoneNumber")))
        role = self.wait.until(EC.presence_of_element_located((By.ID, "role")))

        # Fill form
        name.send_keys("Mila")
        surname.send_keys("Jokic")
        email.send_keys("jokicjokic@example.com")
        password.send_keys("password123")
        confirm_password.send_keys("password123")
        address.send_keys("123 Main St")
        phone_number.send_keys("1234567890")
        role.send_keys("HOST")

        # Scroll into view and click the submit button
        submit_button = self.wait.until(EC.element_to_be_clickable((By.CSS_SELECTOR, "button[type='submit']")))
        driver.execute_script("arguments[0].scrollIntoView(true);", submit_button)
        time.sleep(1)
        submit_button.click()

        # Assert registration success
        success_message = self.wait.until(EC.presence_of_element_located((By.CSS_SELECTOR, ".success-message")))
        self.assertEqual(success_message.text,
                         "Registration successful. Please check your email to activate your account.")

        self.driver.quit()



    def test_registration_form_noName(self):
        driver = self.driver

        # Wait for the elements to be present
        surname = self.wait.until(EC.presence_of_element_located((By.ID, "surname")))
        email = self.wait.until(EC.presence_of_element_located((By.ID, "email")))
        password = self.wait.until(EC.presence_of_element_located((By.ID, "password")))
        confirm_password = self.wait.until(EC.presence_of_element_located((By.ID, "confirmPassword")))
        address = self.wait.until(EC.presence_of_element_located((By.ID, "address")))
        phone_number = self.wait.until(EC.presence_of_element_located((By.ID, "phoneNumber")))
        role = self.wait.until(EC.presence_of_element_located((By.ID, "role")))

        # Fill form
        surname.send_keys("Jokic")
        email.send_keys("jokicjokic@example.com")
        password.send_keys("password123")
        confirm_password.send_keys("password123")
        address.send_keys("123 Main St")
        phone_number.send_keys("1234567890")
        role.send_keys("HOST")

        # Scroll into view and click the submit button
        submit_button = self.wait.until(EC.element_to_be_clickable((By.CSS_SELECTOR, "button[type='submit']")))
        driver.execute_script("arguments[0].scrollIntoView(true);", submit_button)
        time.sleep(1)
        submit_button.click()

        # Assert registration success
        error_message = self.wait.until(EC.presence_of_element_located((By.CLASS_NAME, "invalid-feedback")))
        self.assertEqual(error_message.text,
                         "Name is required")

        self.driver.quit()


    def test_registration_form_Surname(self):
        driver = self.driver

        # Wait for the elements to be present
        name = self.wait.until(EC.presence_of_element_located((By.ID, "name")))
        email = self.wait.until(EC.presence_of_element_located((By.ID, "email")))
        password = self.wait.until(EC.presence_of_element_located((By.ID, "password")))
        confirm_password = self.wait.until(EC.presence_of_element_located((By.ID, "confirmPassword")))
        address = self.wait.until(EC.presence_of_element_located((By.ID, "address")))
        phone_number = self.wait.until(EC.presence_of_element_located((By.ID, "phoneNumber")))
        role = self.wait.until(EC.presence_of_element_located((By.ID, "role")))

        # Fill form
        name.send_keys("Mila")
        email.send_keys("jokicjokic@example.com")
        password.send_keys("password123")
        confirm_password.send_keys("password123")
        address.send_keys("123 Main St")
        phone_number.send_keys("1234567890")
        role.send_keys("HOST")

        # Scroll into view and click the submit button
        submit_button = self.wait.until(EC.element_to_be_clickable((By.CSS_SELECTOR, "button[type='submit']")))
        driver.execute_script("arguments[0].scrollIntoView(true);", submit_button)
        time.sleep(1)
        submit_button.click()

        # Assert registration success
        error_message = self.wait.until(EC.presence_of_element_located((By.CLASS_NAME, "invalid-feedback")))
        self.assertEqual(error_message.text,
                         "Surname is required")

        self.driver.quit()


    def test_registration_form_noEmail(self):
        driver = self.driver

        # Wait for the elements to be present
        name = self.wait.until(EC.presence_of_element_located((By.ID, "name")))
        surname = self.wait.until(EC.presence_of_element_located((By.ID, "surname")))
        password = self.wait.until(EC.presence_of_element_located((By.ID, "password")))
        confirm_password = self.wait.until(EC.presence_of_element_located((By.ID, "confirmPassword")))
        address = self.wait.until(EC.presence_of_element_located((By.ID, "address")))
        phone_number = self.wait.until(EC.presence_of_element_located((By.ID, "phoneNumber")))
        role = self.wait.until(EC.presence_of_element_located((By.ID, "role")))

        # Fill form
        name.send_keys("Mila")
        surname.send_keys("Jokic")
        password.send_keys("password123")
        confirm_password.send_keys("password123")
        address.send_keys("123 Main St")
        phone_number.send_keys("1234567890")
        role.send_keys("HOST")

        # Scroll into view and click the submit button
        submit_button = self.wait.until(EC.element_to_be_clickable((By.CSS_SELECTOR, "button[type='submit']")))
        driver.execute_script("arguments[0].scrollIntoView(true);", submit_button)
        time.sleep(1)
        submit_button.click()

        # Assert registration success
        error_message = self.wait.until(EC.presence_of_element_located((By.CLASS_NAME, "invalid-feedback")))
        self.assertEqual(error_message.text,
                         "Email is required")

        self.driver.quit()


    def test_registration_form_noPassword(self):
        driver = self.driver

        # Wait for the elements to be present
        name = self.wait.until(EC.presence_of_element_located((By.ID, "name")))
        surname = self.wait.until(EC.presence_of_element_located((By.ID, "surname")))
        email = self.wait.until(EC.presence_of_element_located((By.ID, "email")))
        confirm_password = self.wait.until(EC.presence_of_element_located((By.ID, "confirmPassword")))
        address = self.wait.until(EC.presence_of_element_located((By.ID, "address")))
        phone_number = self.wait.until(EC.presence_of_element_located((By.ID, "phoneNumber")))
        role = self.wait.until(EC.presence_of_element_located((By.ID, "role")))

        # Fill form
        name.send_keys("Mila")
        surname.send_keys("Jokic")
        email.send_keys("jokicjokic@example.com")
        confirm_password.send_keys("password123")
        address.send_keys("123 Main St")
        phone_number.send_keys("1234567890")
        role.send_keys("HOST")

        # Scroll into view and click the submit button
        submit_button = self.wait.until(EC.element_to_be_clickable((By.CSS_SELECTOR, "button[type='submit']")))
        driver.execute_script("arguments[0].scrollIntoView(true);", submit_button)
        time.sleep(1)
        submit_button.click()

        # Assert registration success
        error_message = self.wait.until(EC.presence_of_element_located((By.CLASS_NAME, "invalid-feedback")))
        self.assertEqual(error_message.text,
                         "Password is required")

        self.driver.quit()


    def test_registration_form_passwordShort(self):
        driver = self.driver

        # Wait for the elements to be present
        name = self.wait.until(EC.presence_of_element_located((By.ID, "name")))
        surname = self.wait.until(EC.presence_of_element_located((By.ID, "surname")))
        email = self.wait.until(EC.presence_of_element_located((By.ID, "email")))
        password = self.wait.until(EC.presence_of_element_located((By.ID, "password")))
        confirm_password = self.wait.until(EC.presence_of_element_located((By.ID, "confirmPassword")))
        address = self.wait.until(EC.presence_of_element_located((By.ID, "address")))
        phone_number = self.wait.until(EC.presence_of_element_located((By.ID, "phoneNumber")))
        role = self.wait.until(EC.presence_of_element_located((By.ID, "role")))

        # Fill form
        name.send_keys("Mila")
        surname.send_keys("Jokic")
        email.send_keys("jokicjokic@example.com")
        password.send_keys("passw")
        confirm_password.send_keys("password123")
        address.send_keys("123 Main St")
        phone_number.send_keys("1234567890")
        role.send_keys("HOST")

        # Scroll into view and click the submit button
        submit_button = self.wait.until(EC.element_to_be_clickable((By.CSS_SELECTOR, "button[type='submit']")))
        driver.execute_script("arguments[0].scrollIntoView(true);", submit_button)
        time.sleep(1)
        submit_button.click()

        # Assert registration success
        error_message = self.wait.until(EC.presence_of_element_located((By.CLASS_NAME, "invalid-feedback")))
        self.assertEqual(error_message.text,
                         "Password must be at least 6 characters")

        self.driver.quit()


    def test_registration_form_noConfirm(self):
        driver = self.driver

        # Wait for the elements to be present
        name = self.wait.until(EC.presence_of_element_located((By.ID, "name")))
        surname = self.wait.until(EC.presence_of_element_located((By.ID, "surname")))
        email = self.wait.until(EC.presence_of_element_located((By.ID, "email")))
        password = self.wait.until(EC.presence_of_element_located((By.ID, "password")))
        address = self.wait.until(EC.presence_of_element_located((By.ID, "address")))
        phone_number = self.wait.until(EC.presence_of_element_located((By.ID, "phoneNumber")))
        role = self.wait.until(EC.presence_of_element_located((By.ID, "role")))

        # Fill form
        name.send_keys("Mila")
        surname.send_keys("Jokic")
        email.send_keys("jokicjokic@example.com")
        password.send_keys("password123")
        address.send_keys("123 Main St")
        phone_number.send_keys("1234567890")
        role.send_keys("HOST")

        # Scroll into view and click the submit button
        submit_button = self.wait.until(EC.element_to_be_clickable((By.CSS_SELECTOR, "button[type='submit']")))
        driver.execute_script("arguments[0].scrollIntoView(true);", submit_button)
        time.sleep(1)
        submit_button.click()

        # Assert registration success
        error_message = self.wait.until(EC.presence_of_element_located((By.CLASS_NAME, "invalid-feedback")))
        self.assertEqual(error_message.text,
                         "Confirm Password is required")

        self.driver.quit()


    def test_registration_form_passMismatch(self):
        driver = self.driver

        # Wait for the elements to be present
        name = self.wait.until(EC.presence_of_element_located((By.ID, "name")))
        surname = self.wait.until(EC.presence_of_element_located((By.ID, "surname")))
        email = self.wait.until(EC.presence_of_element_located((By.ID, "email")))
        password = self.wait.until(EC.presence_of_element_located((By.ID, "password")))
        confirm_password = self.wait.until(EC.presence_of_element_located((By.ID, "confirmPassword")))
        address = self.wait.until(EC.presence_of_element_located((By.ID, "address")))
        phone_number = self.wait.until(EC.presence_of_element_located((By.ID, "phoneNumber")))
        role = self.wait.until(EC.presence_of_element_located((By.ID, "role")))

        # Fill form
        name.send_keys("Mila")
        surname.send_keys("Jokic")
        email.send_keys("jokicjokic@example.com")
        password.send_keys("1234567")
        confirm_password.send_keys("123456")
        address.send_keys("123 Main St")
        phone_number.send_keys("1234567890")
        role.send_keys("HOST")

        # Scroll into view and click the submit button
        submit_button = self.wait.until(EC.element_to_be_clickable((By.CSS_SELECTOR, "button[type='submit']")))
        driver.execute_script("arguments[0].scrollIntoView(true);", submit_button)
        time.sleep(1)
        submit_button.click()

        # Assert registration success
        error_message = self.wait.until(EC.presence_of_element_located((By.CLASS_NAME, "invalid-feedback")))
        self.assertEqual(error_message.text,
                         "Passwords must match")

        self.driver.quit()


    def test_registration_form_noAddress(self):
        driver = self.driver

        # Wait for the elements to be present
        name = self.wait.until(EC.presence_of_element_located((By.ID, "name")))
        surname = self.wait.until(EC.presence_of_element_located((By.ID, "surname")))
        email = self.wait.until(EC.presence_of_element_located((By.ID, "email")))
        password = self.wait.until(EC.presence_of_element_located((By.ID, "password")))
        confirm_password = self.wait.until(EC.presence_of_element_located((By.ID, "confirmPassword")))
        phone_number = self.wait.until(EC.presence_of_element_located((By.ID, "phoneNumber")))
        role = self.wait.until(EC.presence_of_element_located((By.ID, "role")))

        # Fill form
        name.send_keys("Mila")
        surname.send_keys("Jokic")
        email.send_keys("jokicjokic@example.com")
        password.send_keys("password123")
        confirm_password.send_keys("password123")
        phone_number.send_keys("1234567890")
        role.send_keys("HOST")

        # Scroll into view and click the submit button
        submit_button = self.wait.until(EC.element_to_be_clickable((By.CSS_SELECTOR, "button[type='submit']")))
        driver.execute_script("arguments[0].scrollIntoView(true);", submit_button)
        time.sleep(1)
        submit_button.click()

        # Assert registration success
        error_message = self.wait.until(EC.presence_of_element_located((By.CLASS_NAME, "invalid-feedback")))
        self.assertEqual(error_message.text,
                         "Address is required")

        self.driver.quit()


    def test_registration_form_noPhone(self):
        driver = self.driver

        # Wait for the elements to be present
        name = self.wait.until(EC.presence_of_element_located((By.ID, "name")))
        surname = self.wait.until(EC.presence_of_element_located((By.ID, "surname")))
        email = self.wait.until(EC.presence_of_element_located((By.ID, "email")))
        password = self.wait.until(EC.presence_of_element_located((By.ID, "password")))
        confirm_password = self.wait.until(EC.presence_of_element_located((By.ID, "confirmPassword")))
        address = self.wait.until(EC.presence_of_element_located((By.ID, "address")))
        role = self.wait.until(EC.presence_of_element_located((By.ID, "role")))

        # Fill form
        name.send_keys("Mila")
        surname.send_keys("Jokic")
        email.send_keys("jokicjokic@example.com")
        password.send_keys("password123")
        confirm_password.send_keys("password123")
        address.send_keys("123 Main St")
        role.send_keys("HOST")

        # Scroll into view and click the submit button
        submit_button = self.wait.until(EC.element_to_be_clickable((By.CSS_SELECTOR, "button[type='submit']")))
        driver.execute_script("arguments[0].scrollIntoView(true);", submit_button)
        time.sleep(1)
        submit_button.click()

        # Assert registration success
        error_message = self.wait.until(EC.presence_of_element_located((By.CLASS_NAME, "invalid-feedback")))
        self.assertEqual(error_message.text,
                         "Phone Number is required")

        self.driver.quit()


    def test_registration_form_phoneShort(self):
        driver = self.driver

        # Wait for the elements to be present
        name = self.wait.until(EC.presence_of_element_located((By.ID, "name")))
        surname = self.wait.until(EC.presence_of_element_located((By.ID, "surname")))
        email = self.wait.until(EC.presence_of_element_located((By.ID, "email")))
        password = self.wait.until(EC.presence_of_element_located((By.ID, "password")))
        confirm_password = self.wait.until(EC.presence_of_element_located((By.ID, "confirmPassword")))
        address = self.wait.until(EC.presence_of_element_located((By.ID, "address")))
        phone_number = self.wait.until(EC.presence_of_element_located((By.ID, "phoneNumber")))
        role = self.wait.until(EC.presence_of_element_located((By.ID, "role")))

        # Fill form
        name.send_keys("Mila")
        surname.send_keys("Jokic")
        email.send_keys("jokicjokic@example.com")
        password.send_keys("password123")
        confirm_password.send_keys("password123")
        address.send_keys("123 Main St")
        phone_number.send_keys("123456789")
        role.send_keys("HOST")

        # Scroll into view and click the submit button
        submit_button = self.wait.until(EC.element_to_be_clickable((By.CSS_SELECTOR, "button[type='submit']")))
        driver.execute_script("arguments[0].scrollIntoView(true);", submit_button)
        time.sleep(1)
        submit_button.click()

        # Assert registration success
        error_message = self.wait.until(EC.presence_of_element_located((By.CLASS_NAME, "invalid-feedback")))
        self.assertEqual(error_message.text,
                         "Invalid phone number")

        self.driver.quit()


    def test_registration_form_phoneLong(self):
        driver = self.driver

        # Wait for the elements to be present
        name = self.wait.until(EC.presence_of_element_located((By.ID, "name")))
        surname = self.wait.until(EC.presence_of_element_located((By.ID, "surname")))
        email = self.wait.until(EC.presence_of_element_located((By.ID, "email")))
        password = self.wait.until(EC.presence_of_element_located((By.ID, "password")))
        confirm_password = self.wait.until(EC.presence_of_element_located((By.ID, "confirmPassword")))
        address = self.wait.until(EC.presence_of_element_located((By.ID, "address")))
        phone_number = self.wait.until(EC.presence_of_element_located((By.ID, "phoneNumber")))
        role = self.wait.until(EC.presence_of_element_located((By.ID, "role")))

        # Fill form
        name.send_keys("Mila")
        surname.send_keys("Jokic")
        email.send_keys("jokicjokic@example.com")
        password.send_keys("password123")
        confirm_password.send_keys("password123")
        address.send_keys("123 Main St")
        phone_number.send_keys("12345678900")
        role.send_keys("HOST")

        # Scroll into view and click the submit button
        submit_button = self.wait.until(EC.element_to_be_clickable((By.CSS_SELECTOR, "button[type='submit']")))
        driver.execute_script("arguments[0].scrollIntoView(true);", submit_button)
        time.sleep(1)
        submit_button.click()

        # Assert registration success
        error_message = self.wait.until(EC.presence_of_element_located((By.CLASS_NAME, "invalid-feedback")))
        self.assertEqual(error_message.text,
                         "Invalid phone number")

        self.driver.quit()


    def test_registration_form_phoneCharacters(self):
        driver = self.driver

        # Wait for the elements to be present
        name = self.wait.until(EC.presence_of_element_located((By.ID, "name")))
        surname = self.wait.until(EC.presence_of_element_located((By.ID, "surname")))
        email = self.wait.until(EC.presence_of_element_located((By.ID, "email")))
        password = self.wait.until(EC.presence_of_element_located((By.ID, "password")))
        confirm_password = self.wait.until(EC.presence_of_element_located((By.ID, "confirmPassword")))
        address = self.wait.until(EC.presence_of_element_located((By.ID, "address")))
        phone_number = self.wait.until(EC.presence_of_element_located((By.ID, "phoneNumber")))
        role = self.wait.until(EC.presence_of_element_located((By.ID, "role")))

        # Fill form
        name.send_keys("Mila")
        surname.send_keys("Jokic")
        email.send_keys("jokicjokic@example.com")
        password.send_keys("password123")
        confirm_password.send_keys("password123")
        address.send_keys("123 Main St")
        phone_number.send_keys("abcdef1234")
        role.send_keys("HOST")

        # Scroll into view and click the submit button
        submit_button = self.wait.until(EC.element_to_be_clickable((By.CSS_SELECTOR, "button[type='submit']")))
        driver.execute_script("arguments[0].scrollIntoView(true);", submit_button)
        time.sleep(1)
        submit_button.click()

        # Assert registration success
        error_message = self.wait.until(EC.presence_of_element_located((By.CLASS_NAME, "invalid-feedback")))
        self.assertEqual(error_message.text,
                         "Invalid phone number")

        self.driver.quit()


    def test_registration_form_noRole(self):
        driver = self.driver

        # Wait for the elements to be present
        name = self.wait.until(EC.presence_of_element_located((By.ID, "name")))
        surname = self.wait.until(EC.presence_of_element_located((By.ID, "surname")))
        email = self.wait.until(EC.presence_of_element_located((By.ID, "email")))
        password = self.wait.until(EC.presence_of_element_located((By.ID, "password")))
        confirm_password = self.wait.until(EC.presence_of_element_located((By.ID, "confirmPassword")))
        address = self.wait.until(EC.presence_of_element_located((By.ID, "address")))
        phone_number = self.wait.until(EC.presence_of_element_located((By.ID, "phoneNumber")))

        # Fill form
        name.send_keys("Mila")
        surname.send_keys("Jokic")
        email.send_keys("jokicjokic@example.com")
        password.send_keys("password123")
        confirm_password.send_keys("password123")
        address.send_keys("123 Main St")
        phone_number.send_keys("1234567890")

        # Scroll into view and click the submit button
        submit_button = self.wait.until(EC.element_to_be_clickable((By.CSS_SELECTOR, "button[type='submit']")))
        driver.execute_script("arguments[0].scrollIntoView(true);", submit_button)
        time.sleep(1)
        submit_button.click()

        # Assert registration success
        error_message = self.wait.until(EC.presence_of_element_located((By.CLASS_NAME, "invalid-feedback")))
        self.assertEqual(error_message.text,
                         "Role is required")

        self.driver.quit()


    def test_registration_form_emailExist(self):
        driver = self.driver

        # Wait for the elements to be present
        name = self.wait.until(EC.presence_of_element_located((By.ID, "name")))
        surname = self.wait.until(EC.presence_of_element_located((By.ID, "surname")))
        email = self.wait.until(EC.presence_of_element_located((By.ID, "email")))
        password = self.wait.until(EC.presence_of_element_located((By.ID, "password")))
        confirm_password = self.wait.until(EC.presence_of_element_located((By.ID, "confirmPassword")))
        address = self.wait.until(EC.presence_of_element_located((By.ID, "address")))
        phone_number = self.wait.until(EC.presence_of_element_located((By.ID, "phoneNumber")))
        role = self.wait.until(EC.presence_of_element_located((By.ID, "role")))

        # Fill form
        name.send_keys("Mila")
        surname.send_keys("Jokic")
        email.send_keys("jokicjokic@example.com")
        password.send_keys("password123")
        confirm_password.send_keys("password123")
        address.send_keys("123 Main St")
        phone_number.send_keys("1234567890")
        role.send_keys("HOST")

        # Scroll into view and click the submit button
        submit_button = self.wait.until(EC.element_to_be_clickable((By.CSS_SELECTOR, "button[type='submit']")))
        driver.execute_script("arguments[0].scrollIntoView(true);", submit_button)
        time.sleep(1)
        submit_button.click()

        # Assert registration success
        error_message = self.wait.until(EC.presence_of_element_located((By.CLASS_NAME, "error-message")))
        self.assertEqual(error_message.text,
                         "Registration failed. Please try again.")

        self.driver.quit()


if __name__ == "__main__":
    unittest.main()
