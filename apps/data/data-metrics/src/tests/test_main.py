import pytest
import main as sut
from importlib import import_module


def test_should_raises_error_when_invalid_arguments():
    args = ['no_subparser', '--arg1', 'value1']
    modules = {}

    with pytest.raises(SystemExit):
        sut.parse_cli_arguments(args, modules)


def test_should_parse_arguments_when_valid():
    args=['example', '--var', '1']
    modules={ 'example': import_module('tests.jobs.fake')}

    arguments = sut.parse_cli_arguments(args, modules)

    assert "action" in arguments
    assert "var" in arguments
    

    