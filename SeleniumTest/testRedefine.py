
from selenium import webdriver
from selenium.webdriver.common.by import By
from selenium.webdriver.support.ui import WebDriverWait
from selenium.webdriver.support import expected_conditions as EC
import unittest
import time


class RegistrationTest(unittest.TestCase):
    def setUp(self):
        self.driver = webdriver.Chrome()
        self.driver.get("http://localhost:4200/accommodation/1")
        self.wait = WebDriverWait(self.driver, 10)  # Wait for up to 10 seconds


    def test_registration_form_good(self):
        driver = self.driver

        dates = self.wait.until(EC.presence_of_element_located((By.ID, "newDates")))

        dates.send_keys("2025-03-03,2025-04-04")

        submit_button = self.wait.until(EC.element_to_be_clickable((By.ID, "dugme")))
        driver.execute_script("arguments[0].scrollIntoView(true);", submit_button)
        time.sleep(1)
        submit_button.click()

        message = self.wait.until(EC.presence_of_element_located((By.CLASS_NAME, "response-message")))
        self.assertEqual(message.text,
                         "Accommodation redefined successfully!")

        self.driver.quit()



    def test_registration_form_bad(self):
        driver = self.driver

        dates = self.wait.until(EC.presence_of_element_located((By.ID, "newDates")))

        dates.send_keys("2024-09-10")

        submit_button = self.wait.until(EC.element_to_be_clickable((By.ID, "dugme")))
        driver.execute_script("arguments[0].scrollIntoView(true);", submit_button)
        time.sleep(1)
        submit_button.click()

        message = self.wait.until(EC.presence_of_element_located((By.CLASS_NAME, "response-message")))
        self.assertEqual(message.text,
                         "ERROR")

        self.driver.quit()


if __name__ == "__main__":
    unittest.main()
