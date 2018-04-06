from unittest import TestCase
from selenium import webdriver
from time import sleep
import os


class TestTheInternet(TestCase):

    def setUp(self):
        desired_cap = {
            'browserName': "chrome",
            'platformName': "Windows 10",
            'version': "62",
            'tags': [ "python" ],
            'build': 'Py2'
        }
        _user = os.environ['SAUCE_USERNAME']
        _key = os.environ['SAUCE_ACCESS_KEY']
        url = 'http://{}:{}@ondemand.saucelabs.com:80/wd/hub'.format(_user, _key)

        self.driver = webdriver.Remote(
            command_executor=url,
            desired_capabilities=desired_cap)

    def tearDown(self):
        self.driver.quit()

    def test_page_title(self):
        self.driver.get('http://the-internet.herokuapp.com')

        assert self.driver.title == 'The Internet'

    def test_checkboxes(self):
        self.driver.get('http://the-internet.herokuapp.com/checkboxes')

        expected = self.driver.find_element_by_class_name('example').text

        assert 'Checkboxes' in expected
