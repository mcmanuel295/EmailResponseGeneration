// Placeholder function for your backend integration
function generateResponse() {
        const receivedEmail = document.getElementById('receivedEmail').value;
        const selectedTone = document.getElementById('toneSelect').value;
        const outputBox = document.getElementById('generatedResponse');

        if (!receivedEmail.trim()) {
            alert('Please paste an incoming email first.');
            return;
        }

        outputBox.value = `[Loading...] Generating a ${selectedTone} response...`;

        // TODO: Add your fetch() API code here to call your backend pipeline.
        fetch("http://localhost:8080/api/email/generate", {
            method: "POST",
            headers: {
                "Content-Type": "application/json"
            },
            body: JSON.stringify({
                email: receivedEmail,
                tone: selectedTone
            })
        })
        .then(response => response.json())
        .then(data => {
            outputBox.value = data.response;
        })
        .catch(error => {
            console.error("Error generating response:", error);
            outputBox.value = "Error generating response. Please try again.";
        });


        // Example simulation:
        // setTimeout(() => {
            // outputBox.value = `Dear Sender,\n\nThank you for reaching out. This is a placeholder sample showing a ${selectedTone} reply to your message.\n\nBest regards,\n[Your Name]`;
        // }, 1000);
    }

    // Copy to Clipboard logic
    function copyToClipboard() {
        const responseText = document.getElementById('generatedResponse');
        const copyBtn = document.getElementById('copyBtn');

        if (!responseText.value.trim() || responseText.value.startsWith('[Loading...]')) {
            return;
        }

        responseText.select();
        responseText.setSelectionRange(0, 99999); // For mobile devices

        navigator.clipboard.writeText(responseText.value).then(() => {
            // Visual feedback on success
            const originalText = copyBtn.innerHTML;
            copyBtn.classList.add('success');
            copyBtn.innerHTML = '✓ Copied!';

            setTimeout(() => {
                copyBtn.classList.remove('success');
                copyBtn.innerHTML = originalText;
            }, 2000);
        }).catch(err => {
            console.error('Failed to copy text: ', err);
        });
    }