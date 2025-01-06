document.addEventListener("DOMContentLoaded", function () {

    const loginForm = document.getElementById('loginForm');

    loginForm.addEventListener('submit', function (event) {
        event.preventDefault(); // Prevent form submission for validation

        const username = document.getElementById('username').value.trim();
        const password = document.getElementById('password').value.trim();

        // Simple validation
        if (username === "" || password === "") {
            showError("Please fill in all fields.");
            return; // Stop submission
        }

        // If validation passes, submit the form
        loginForm.submit();

        // Reset error message
        clearError();

        function showError(message) {
            const errorMessage = document.getElementById('error-message');
            errorMessage.textContent = message;
            errorMessage.style.display = 'block';
        }

        function clearError() {
            const errorMessage = document.getElementById('error-message');
            errorMessage.textContent = '';
            errorMessage.style.display = 'none';
        }

        // Additional AJAX handling can be added here if needed.

    });
});

