adminListForm = document.getElementById('adminListForm');

function pageNext() {
    adminListForm.setAttribute('method', 'post');
    adminListForm.setAttribute('action', '${ctxPath}/admin/product/list.do?pg=${pageGroupEnd + 1}');
    adminListForm.submit();
}

function pagePrev() {
    adminListForm.setAttribute('method', 'post');
    adminListForm.setAttribute('action', '${ctxPath}/admin/product/list.do?pg=${pageGroupStart - 1}');
    adminListForm.submit();
}

function pageNum(e) {
    adminListForm.setAttribute('method', 'post');
    adminListForm.setAttribute('action', '${ctxPath}/admin/product/list.do?pg=' + e);
    adminListForm.submit();
}