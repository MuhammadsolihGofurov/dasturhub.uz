const handleSoon = () => {
    Toastify({
        text: "Tez kunda qo'shiladi...",
        duration: 3000,
        gravity: "top", // `top` yoki `bottom`
        position: "right", // `left`, `center` yoki `right`
        stopOnFocus: true, // Sichqoncha ustiga borganda to'xtab turadi
    }).showToast();
}

function deleteCourse(id) {
    if (confirm("Haqiqatan ham ushbu kursni o'chirib tashlamoqchimisiz?")) {
        fetch(`/courses/api/manage/${id}`, {
            method: 'DELETE',
            headers: {
                'Content-Type': 'application/json'
            }
        }).then(response => {
            if (response.ok) {
                Toastify({
                    text: "Kurs muvaffaqiyatli o'chirildi",
                    gravity: "top",
                    position: "right",
                    stopOnFocus: true,
                    type:"success"
                }).showToast();

                // 1 soniyadan keyin sahifani yangilash
                setTimeout(() => location.reload(), 1000);
            } else {
                alert("O'chirishda xatolik yuz berdi!");
            }
        }).catch(err => {
            console.error("Xatolik:", err);
            alert("Server bilan bog'lanishda xatolik!");
        });
    }
}

function deleteSection(id) {
    if (confirm("Haqiqatan ham ushbu bo'limni o'chirib tashlamoqchimisiz?")) {
        fetch(`/sections/api/manage/${id}`, {
            method: 'DELETE',
            headers: {
                'Content-Type': 'application/json'
            }
        }).then(response => {
            if (response.ok) {
                Toastify({
                    text: "Bo'lim muvaffaqiyatli o'chirildi",
                    gravity: "top",
                    position: "right",
                    stopOnFocus: true,
                    type:"success"
                }).showToast();

                // 1 soniyadan keyin sahifani yangilash
                setTimeout(() => location.reload(), 1000);
            } else {
                alert("O'chirishda xatolik yuz berdi!");
            }
        }).catch(err => {
            console.error("Xatolik:", err);
            alert("Server bilan bog'lanishda xatolik!");
        });
    }
}