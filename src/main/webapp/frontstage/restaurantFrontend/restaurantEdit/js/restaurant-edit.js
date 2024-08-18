$(document).ready(function() {
    const contextPath = window.location.pathname.substring(0, window.location.pathname.indexOf("/", 2));

    $('#imageInput').on('change', function(event) {
        const file = event.target.files[0];
        if (file) {
            const reader = new FileReader();
            reader.onload = function(e) {
                $('#imagePreview').attr('src', e.target.result).show();
            };
            reader.readAsDataURL(file);
        } else {
            $('#imagePreview').hide();
        }
    });

    function loadRestaurantInfo() {
        $.ajax({
            url: `${contextPath}/rest/rest.do?action=findIdByUser`,
            type: 'POST',
            contentType: 'application/json',
            success: function(response) {
                if (response) {
                    $('#restName').val(response.restName);
                    $('#location').val(response.location);
                    $('#phone').val(response.phone);
                    $('#description').val(response.description);
                } else {
                    console.error('Response is null or undefined');
                }
            },
            error: function(xhr, status, error) {
                console.error('查詢餐廳信息時出錯:', error);
            }
        });
    }

    loadRestaurantInfo();
});
