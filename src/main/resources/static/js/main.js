const handleSoon = () => {
    Toastify({
        text: "Tez kunda qo'shiladi...",
        duration: 3000,
        gravity: "top", // `top` yoki `bottom`
        position: "right", // `left`, `center` yoki `right`
        stopOnFocus: true, // Sichqoncha ustiga borganda to'xtab turadi
    }).showToast();
}