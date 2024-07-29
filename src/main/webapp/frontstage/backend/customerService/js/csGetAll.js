$(document).ready(function() {
	const contextPath = window.location.pathname.substring(0, window.location.pathname.indexOf("/", 2));
	let currentPage = 1;  // 當前頁碼
	const dataList = $('#data-list');

	//cs.html路徑位置
	function goToCSPage() {
		window.location.href = 'cs.html';
	}
	//=======================================創建loadAnnouncements=======================================
	// 初次加載公告數據
	loadCustomerServices(currentPage, contextPath);
	// 分頁按鈕事件
	//上一頁
	$('#prev-page').on('click', function() {
		if (currentPage > 1) {
			currentPage--;
			loadCustomerServices(currentPage, contextPath);
		} else {
			$('#prev-page').prop('disabled', false);
		}
	});
	//下一頁
	$('#next-page').on('click', function() {
		currentPage++;
		loadCustomerServices(currentPage, contextPath);
	});
	//最後一頁
	$('#last-page').on('click', function() {
		$.ajax({
			url: `${contextPath}/cs/cs.do?action=getAll`,
			type: 'GET',
			success: function(data) {
				currentPage = data.totalPageQty;
				loadCustomerServices(currentPage, contextPath);
			},
			error: function(error) {
				console.error('取得公告時出錯:', error);
			}
		});
	});
	//=======================================查詢全部=======================================
	function loadCustomerServices(page, contextPath) {
		$.ajax({
			url: `${contextPath}/cs/cs.do?action=getAll`,
			type: 'GET',
			data: {
				page: page,
			},
			success: function(data) {
				dataList.empty();
				if (data && Array.isArray(data.List)) {
					data.List.forEach(cs => {
						
						// 設定內容文字的最大長度
						let maxContentLength = 20;
						let feedbackContentText = cs.feedbackContent.length > maxContentLength
							? cs.feedbackContent.substring(0, maxContentLength) + "..."
							: cs.feedbackContent;
							
						// 設定類型1.帳號問題2.食安問題3.訂單問題4.系統問題5.其他
						var feedbackType;
						switch(cs.feedbackType){
							case 1 :
								feedbackType = "帳號問題"
								break;
							case 2 :
								feedbackType = "食安問題"
								break;
							case 3 :
								feedbackType = "訂單問題"
								break;
							case 4 :
								feedbackType = "系統問題"
								break;
							case 5 :
								feedbackType = "其他"
								break;
						}

						//如果1.已回覆 2.未回覆
						var replyStatus;
						if (cs.replyStatus) {
							replyStatus = "已回覆";
						} else {
							replyStatus = "未回覆";
						}
						dataList.append(`
                        <tr>
                            <td>${cs.csId}</td>
                            <td>${cs.restaurant.restName}</td>
                            <td>${feedbackType}</td>
                            <td>${feedbackContentText}</td>
                            <td>${replyStatus}</td>
                            <td>${cs.feedbackTime}</td>
                            <td>
								<button value="${cs.csId}" type="button" id="update-button"><img id="edit" src="image/edit.png" alt="圖示"></button>
								<button value="${cs.csId}" type="button" id="remove-button"><img id="delete" src="image/delete.png" alt="圖示"></button>
							</td>
                        </tr>
                    `);
					});
					$('#current-page').text(data.currentPage);  // 更新當前頁碼顯示
					if (data.currentPage == data.totalPageQty) {
						$('#next-page').prop('disabled', true);
					} else {
						$('#next-page').prop('disabled', false);
					}
					if (data.currentPage == 1) {
						$('#prev-page').prop('disabled', true);
					} else {
						$('#prev-page').prop('disabled', false);
					}
				} else {
					console.error('數據格式錯誤：沒有 csList 數組');
				}
			},
			error: function(error) {
				console.error('取得客服反饋時出錯:', error);
			}
		});
	}
	let page = 1;  // 默認設置為第1頁
	loadCustomerServices(page, contextPath);
});
