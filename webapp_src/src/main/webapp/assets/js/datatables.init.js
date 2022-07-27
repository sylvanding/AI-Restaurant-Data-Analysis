$("#alternative-page-datatable").DataTable({
    pagingType: "full_numbers",
    drawCallback: function () {
        $(".dataTables_paginate > .pagination").addClass("pagination-rounded"), $(".dataTables_length select").addClass("form-select form-select-sm")
    }
})
