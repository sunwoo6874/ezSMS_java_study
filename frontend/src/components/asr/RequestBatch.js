import axios from 'axios';
import React, { useState } from 'react';
import '../../css/multiInput.css';
import '../../css/style.css';



function MultiInputComponent() {
    const [ableToRequestASR, setCondition] = useState(false);

    const [inputValues, setInputValues] = useState({
        selectedFile: null,
        serverIp: '',
        portNum: '',
        productcode: '',
        transactionID: '',
        language: '',
        spkd: '',
        align: ''
    });

    // Function to handle input changes
    const handleInputChange = (event) => {
        const { name, value } = event.target;
        setInputValues({
            ...inputValues, [name]: value
        });
        console.log('Ready to go? ', checkCondition());
    };

    // Function to handle file selection
    const handleFileChange = (event) => {
        const selectedFile = event.target.files[0]; // Get the first file selected
        console.log('Selected file:', selectedFile.name + ', type:', selectedFile.type);

        if (selectedFile) {
            setInputValues({
                ...inputValues, selectedFile // Store the file object
            });

            const fr = new FileReader();

            // read file 
            fr.readAsArrayBuffer(selectedFile);

            // file was read and loaded.
            fr.onload = function (event) {
                const fileContent = event.target.result;
                console.log('fileContent:', fileContent);
                console.log('File name:', selectedFile.name);
                console.log('File size:', selectedFile.size);
                console.log('File type:', selectedFile.type);
                console.log('Ready to go? ', checkCondition());
            }
        } else {
            console.log('No file selected');
        }
    };

    const checkCondition = () => {
        const isReady = Object.values(inputValues).every(value => value !== '' && value !== null);
        if (isReady) {
            setCondition(isReady);
            return isReady;
        } else {
            return isReady;
        }
    }

    const SendRequest = () => {
        if (inputValues.selectedFile) {
            let api = '/ezsms/requestBatch';
            const selectedFile = inputValues.selectedFile
            let ipAddress = inputValues.serverIp
            let port = inputValues.portNum
            let pCode = inputValues.productcode
            let transID = inputValues.transactionID
            let lang = inputValues.language
            let spkd = inputValues.spkd
            let align = inputValues.align

            let uri = api + '/?ip=' + ipAddress + '&port=' + port + '&productcode=' + pCode + '&transactionid=' + transID + '&language=' + lang + '&spkd=' + spkd + '&align=' + align;

            console.log('Selected File Size:', selectedFile.size);


            let formData = new FormData();
            formData.append('audio', selectedFile);


            axios.post(uri, formData, {
                headers: {
                    'Content-Type': 'multipart/form-data',
                }
            }).then((response) => console.log('Audio file uploaded:', response.data))
                .catch((error) => console.error('Error uploading audio file', error));

            // axios.post(api, formData, {
            //     headers: {
            //         'Content-Type': 'multipart/form-data',
            //     }
            // }).then((response) => console.log('Audio file uploaded:', response.data))
            //     .catch((error) => console.error('Error uploading audio file', error));
        } else {
            console.error('File is Null or not Selected');
        }
    }

    return (
        <div className="input-container">
            <div className="input-section">
                <label>
                    File Path:
                    <input
                        type="file"
                        name="filepath"
                        id="fileInput"
                        accept='wav'
                        onChange={handleFileChange}
                    />
                </label>
                <br />
                <label>
                    Server IP(def:192.168.10.30):
                    <input
                        type="text"
                        name="serverIp"
                        value={inputValues.serverIp}
                        onChange={handleInputChange}
                    />
                </label>
                <br />
                <label>
                    Port(def:7777):
                    <input
                        type="text"
                        name="portNum"
                        value={inputValues.portNum}
                        onChange={handleInputChange}
                    />
                </label>
                <br />
                <label>
                    Product Code(def:PRODUCT_CODE):
                    <input
                        type="text"
                        name="productcode"
                        value={inputValues.productcode}
                        onChange={handleInputChange}
                    />
                </label>
                <br />
                <label>
                    Transaction ID(ex:01):
                    <input
                        type="text"
                        name="transactionID"
                        value={inputValues.transactionID}
                        onChange={handleInputChange}
                    />
                </label>
                <br />
                <label>
                    Language(def:kor):
                    <input
                        type="text"
                        name="language"
                        value={inputValues.language}
                        onChange={handleInputChange}
                    />
                </label>
                <br />
                <label>
                    Spkd(yes/no, def:no):
                    <input
                        type="text"
                        name="spkd"
                        value={inputValues.spkd}
                        onChange={handleInputChange}
                    />
                </label>
                <br />
                <label>
                    Align(yes/no, def:no):
                    <input
                        type="text"
                        name="align"
                        value={inputValues.align}
                        onChange={handleInputChange}
                    />
                </label>
                <br />
            </div>
            <div className="display-section">
                <p>File Name: {inputValues.selectedFile ? inputValues.selectedFile.name : 'No file selected'}</p>
                <p>Server IP: {inputValues.serverIp}</p>
                <p>Port Number: {inputValues.portNum}</p>
                <p>PRODUCT CODE: {inputValues.productcode}</p>
                <p>Transaction ID: {inputValues.transactionID}</p>
                <p>Language: {inputValues.language}</p>
                <p>Speaker Diarization: {inputValues.spkd}</p>
                <p>Align: {inputValues.align}</p>
            </div>
            <div className='my-button'>
                <button disabled={!ableToRequestASR} onClick={SendRequest} > REQUEST BATCH </button>
            </div>
        </div>
    );
}

export default MultiInputComponent;
