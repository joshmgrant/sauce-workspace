from unittest import TestCase
from selenium import webdriver
from time import sleep
import os


class TestTheInternet(TestCase):

    def setUp(self):
        desired_cap = {
            'browserName': "chrome",
            'version': "62",
            'tags': [ "python" ],
            'name': 'PyCase'
        }
        _user = os.environ['SAUCE_USER']
        _key = os.environ['SAUCE_KEY']
        url = 'http://{}:{}@ondemand.saucelabs.com:80/wd/hub'.format(_user, _key)

        self.driver = webdriver.Remote(
            command_executor=url,
            desired_capabilities=desired_cap)

    def tearDown(self):
        self.driver.quit()

    def test_page_title(self):
        self.driver.get('http://the-internet.herokuapp.com')

        assert self.driver.title == 'The Internet'

    def est_form_auth(self):
        self.driver.get('http://the-internet.herokuapp.com/login')

        self.driver.find_element_by_id('username').send_keys('tomsmith')
        self.driver.find_element_by_id('password').send_keys('SuperSecretPassword!')
        self.driver.find_element_by_class_name('radius').click()

        assert self.driver.find_element_by_class_name('icon-lock').is_displayed()

    def test_checkboxes(self):
        self.driver.get('http://the-internet.herokuapp.com/checkboxes')

        expected = self.driver.find_element_by_class_name('example').text

        assert 'Checkboxes' in expected
