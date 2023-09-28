//todo - error handling

const toJson = async (data) => {
    const resJson = await (async () => {
        try {
            const dataCopy = data.clone();
            return await dataCopy.json();
        }
        catch (e) {
            const dataText = await data.text();
            console.log("Error: " + dataText);
            return "";
        }
    })();

    console.log(resJson);

    return resJson;
}

export const apiPost = async (URL, payload) => {
    const res = await fetch(URL, {
        method: "POST",
        headers: {
            'Content-Type': 'application/json'
        },
        body: JSON.stringify(payload)
    });

    return toJson(res);
};

export const apiDelete = async (URL) => {
    const res = await fetch(URL, {
        method: "DELETE",
        headers: {
            'Content-Type': 'application/json'
        }
    });

    return toJson(res);
}

export const apiGet = async (URL) => {
    const res = await fetch(URL);

    return toJson(res);
}

export const apiPut = async (URL, payload) => {
    const res = await fetch(URL, {
        method: "PUT",
        headers: {
            'Content-Type': 'application/json'
        },
        body: JSON.stringify(payload)
    })

    return toJson(res);
}