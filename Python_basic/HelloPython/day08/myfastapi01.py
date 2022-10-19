from fastapi import FastAPI
from fastapi.param_functions import Form
from fastapi.responses import HTMLResponse

app = FastAPI()


@app.get("/")
async def root():
    return {"message": "Hello World"}


@app.get("/hello", response_class=HTMLResponse)
async def hello():
    return """
    <html>
        <head>
            <title>Some HTML in here</title>
        </head>
        <body>
            <h1>Look ma! HTML!</h1>
        </body>
    </html>
    """

    
@app.post("/dan/", response_class=HTMLResponse)
async def dan_post(dan: str=Form()):
    idan = int(dan)
    html = ""
    
    html += f"{idan}*{1}={idan*1}<br>"
    html += f"{idan}*{2}={idan*2}<br>"
    html += f"{idan}*{3}={idan*3}<br>"
    html += f"{idan}*{4}={idan*4}<br>"
    html += f"{idan}*{5}={idan*5}<br>"
    html += f"{idan}*{6}={idan*6}<br>"
    html += f"{idan}*{7}={idan*7}<br>"
    html += f"{idan}*{8}={idan*8}<br>"
    html += f"{idan}*{9}={idan*9}<br>"

    return f"""
    <html>
        <head>
            <title>구구단</title>
        </head>
        <body>
            {html}
        </body>
    </html>
    """
    
    
@app.get("/dan", response_class=HTMLResponse)
async def dan(dan: str="9"):
    idan = int(dan)
    html = ""
    
    html += f"{idan}*{1}={idan*1}<br>"
    html += f"{idan}*{2}={idan*2}<br>"
    html += f"{idan}*{3}={idan*3}<br>"
    html += f"{idan}*{4}={idan*4}<br>"
    html += f"{idan}*{5}={idan*5}<br>"
    html += f"{idan}*{6}={idan*6}<br>"
    html += f"{idan}*{7}={idan*7}<br>"
    html += f"{idan}*{8}={idan*8}<br>"
    html += f"{idan}*{9}={idan*9}<br>"

    return f"""
    <html>
        <head>
            <title>구구단</title>
        </head>
        <body>
            {html}
        </body>
    </html>
    """

# uvicorn myfastapi02:app --reload
