var main = {
    init : function () {
        var _this = this;
        $('#btn-save').click(function () {
            _this.save();
        });
    },
    save : function () {
        var data = {
            weight: $('#weight').val(),
            height: $('#height').val(),
            content: $('#content').val()
        };

        $.ajax({
            type: 'POST',
            url: '/review/post',
            dataType: 'json',
            contentType:'application/json; charset=utf-8',
            data: JSON.stringify(data)
        }).done(function() {
            alert('글이 등록되었습니다.');
            window.location.href = '/';
        }).fail(function (error) {
            alert(JSON.stringify(error));
        });
    }
};

main.init();