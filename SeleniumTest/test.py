
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

    def test_registration_form(self):
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
        time.sleep(3)
        submit_button.click()

        # Assert registration success
        success_message = self.wait.until(EC.presence_of_element_located((By.CSS_SELECTOR, ".success-message")))
        self.assertEqual(success_message.text,
                         "Registration successful. Please check your email to activate your account.")

        self.driver.quit()


if __name__ == "__main__":
    unittest.main()
