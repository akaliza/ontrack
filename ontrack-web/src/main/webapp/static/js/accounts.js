var Accounts = function () {

    function createAccount () {
		Application.dialogAndSubmit({
			id: 'account-create-dialog',
			title: loc('account.new'),
			url: 'ui/admin/accounts',
			openFn: function () {
                $('#mode').unbind('change');
                $('#mode').change(function () {
                    var mode = $('#mode').val();
                    var password = (mode == 'builtin');
                    if (password) {
                        $('#password-line').show();
                        $('#password').attr('required', 'required');
                        $('#passwordConfirm-line').show();
                        $('#passwordConfirm').attr('required', 'required');
                    } else {
                        $('#password-line').hide();
                        $('#password').removeAttr('required');
                        $('#passwordConfirm-line').hide();
                        $('#passwordConfirm').removeAttr('required');
                    }
                });
			},
			validateFn: function () {
                var mode = $('#mode').val();
			    if (mode == 'builtin') {
			        var password = $('#password').val();
			        var confirm = $('#passwordConfirm').val();
			        if (password != confirm) {
                        Application.dialogError('account-create-dialog', loc('account.password.confirm.incorrect'));
			            return false;
			        } else {
			            return true;
			        }
			    } else {
			        return true;
			    }
			},
			successFn: function (data) {
				location = 'gui/admin/accounts';
			}
		});
    }

    return {
        createAccount: createAccount
    };

} ();